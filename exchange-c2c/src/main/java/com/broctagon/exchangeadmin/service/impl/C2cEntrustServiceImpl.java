
package com.broctagon.exchangeadmin.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.broctagon.exchangeadmin.dao.C2cEntrustDao;
import com.broctagon.exchangeadmin.dao.C2cTradeDao;
import com.broctagon.exchangeadmin.listener.MessageDispatcher;
import com.broctagon.exchangeadmin.message.C2cEntrustCancelReq;
import com.broctagon.exchangeadmin.message.C2cEntrustListReq;
import com.broctagon.exchangeadmin.message.C2cEntrustListRes;
import com.broctagon.exchangeadmin.message.C2cEntrustReq;
import com.broctagon.exchangeadmin.message.C2cResultRes;
import com.broctagon.exchangeadmin.message.C2cUserEntrustListRes;
import com.broctagon.exchangeadmin.message.ManagerFreezeRes;
import com.broctagon.exchangeadmin.model.C2cEntrustModel;
import com.broctagon.exchangeadmin.service.C2cEntrustService;
import com.broctagon.exchangeadmin.util.C2cConstants;

/**
* @auther: Water
* @time: 15 Mar 2018 10:55:12
* 
*/

@Service
@Transactional
public class C2cEntrustServiceImpl implements C2cEntrustService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private C2cEntrustDao c2cEntrustDao;
	
	@Autowired
	private C2cTradeDao c2cTradeDao;
	
	@Autowired
	private MessageDispatcher messageDispatcher;

	public C2cResultRes addEntrust(String req){	
		C2cEntrustReq c2cEntrustMsg = JSON.parseObject(req, C2cEntrustReq.class);
		C2cEntrustModel c2cEntrustModel = new C2cEntrustModel();
		
		int userId = c2cEntrustModel.getUserId();
		double amount = c2cEntrustModel.getAmount();
		String coinName = c2cEntrustModel.getCoinName();
		C2cResultRes c2cResultRes = new C2cResultRes();	
		
		Map<String,Object> userAssetData = c2cTradeDao.selectUserAssets(userId,coinName);
		if(Double.valueOf(userAssetData.get("Available").toString()) < amount) {
			c2cEntrustModel.setCoinName(c2cEntrustMsg.getCoinName());
			c2cEntrustModel.setAmount(c2cEntrustMsg.getAmount());
			c2cEntrustModel.setPrice(c2cEntrustMsg.getPrice());
			c2cEntrustModel.setUserId(c2cEntrustMsg.getUserId());
			c2cEntrustModel.setEntrustType(c2cEntrustMsg.getEntrustType());
			c2cEntrustModel.setStatus(C2cConstants.ENTRUST_STATUS_PENDING);
			int result = c2cEntrustDao.save(c2cEntrustModel);
			
			if(result>0){
				
				c2cResultRes.setResult(true);
				if(c2cEntrustModel.getEntrustType() == C2cConstants.ENTRUST_TYPE_SELL){				
					// freeze the entrust coin mount in the trader assert					
					Integer lastId = c2cEntrustDao.findLastId();
					logger.info("findLastId:" + lastId);
					if(lastId!=null){
//						ManagerFreezeReq freezeReq = new ManagerFreezeReq();
//						freezeReq.setOrderID(lastId);			
//						freezeReq.setOrderNumber(c2cEntrustModel.getAmount());
//						freezeReq.setSymbol(c2cEntrustModel.getCoinName());
//						freezeReq.setTransType(c2cEntrustModel.getEntrustType());
//						freezeReq.setUserID(c2cEntrustModel.getUserId());
//						freezeReq.setTag(RabbitmqConstants.TAG_MANAGER_FREEZE_REQ);				
//						freezeReq.setSrvIdentify(RabbitmqConstants.MQ_KEY_MANAGER_RES);
//						freezeReq.setPrice(c2cEntrustModel.getPrice());	
//						logger.info("JSON.toJSONString(managerUnfreezeReq):" + JSON.toJSONString(freezeReq));
//						messageDispatcher.sendToManager(JSON.toJSONString(freezeReq));
						
						c2cEntrustDao.updateAssets1(userId,amount,coinName);
						
						messageDispatcher.userAssets(req, null);
					}
				}
				
				logger.info("Succeed to entrust:" + req);
			}
			else{
				c2cResultRes.setResult(false);
			}
		}else{
			c2cResultRes.setResult(false);
		}
				
		return c2cResultRes;
	}
	
	public C2cResultRes cancelEntrust(String req){	
		C2cEntrustCancelReq c2cEntrustCancelReq = JSON.parseObject(req, C2cEntrustCancelReq.class);
		
		C2cEntrustModel updateC2cEntrustModel = new C2cEntrustModel();
		updateC2cEntrustModel.setId(c2cEntrustCancelReq.getId());
		updateC2cEntrustModel.setStatus(C2cConstants.ENTRUST_STATUS_CANCEL);
		int result = c2cEntrustDao.updateStatus(updateC2cEntrustModel);
		
		C2cEntrustModel c2cEntrustModel = c2cEntrustDao.findById(c2cEntrustCancelReq.getId());
		C2cResultRes c2cResultRes = new C2cResultRes();	
		if(result>0){
			c2cResultRes.setResult(true);
			if(c2cEntrustModel.getEntrustType() == C2cConstants.ENTRUST_TYPE_SELL){				
				// unfreeze the entrust coin mount in the trader assert				
//				ManagerFreezeReq unfreezeReq = new ManagerFreezeReq();
//				unfreezeReq.setOrderID(c2cEntrustModel.getId());			
//				unfreezeReq.setOrderNumber(c2cEntrustModel.getAmount());
//				unfreezeReq.setSymbol(c2cEntrustModel.getCoinName());
//				unfreezeReq.setTransType(c2cEntrustModel.getEntrustType());
//				unfreezeReq.setUserID(c2cEntrustModel.getUserId());				
//				unfreezeReq.setTag(RabbitmqConstants.TAG_MANAGER_UNFREEZE_REQ);
//				unfreezeReq.setPrice(c2cEntrustModel.getPrice());
//				
//				messageDispatcher.sendToManager(JSON.toJSONString(unfreezeReq));
				
				int userId = c2cEntrustModel.getUserId();
				double count = c2cEntrustModel.getAmount();
				String coinName = c2cEntrustModel.getCoinName();
				c2cEntrustDao.updateAssets(userId,count,coinName);
				
				messageDispatcher.userAssets(req, null);
				
			}
			
			logger.info("Succeed to cancel entrust:" + req);
		}
		else{
			c2cResultRes.setResult(false);
		}		
		return c2cResultRes;
	}
	
	public void freezonRes(String req){
		ManagerFreezeRes managerFreezeRes = JSON.parseObject(req, ManagerFreezeRes.class);	
		if(managerFreezeRes.getStatus()!=0){
			C2cEntrustModel c2cEntrustModel = new C2cEntrustModel();
			c2cEntrustModel.setId(managerFreezeRes.getOrderID());
			c2cEntrustModel.setStatus(C2cConstants.ENTRUST_STATUS_CANCEL);
			int result = c2cEntrustDao.updateStatus(c2cEntrustModel);
			if(result<=0){
				logger.warn("Fail to cancel entrust when fail to freezon");
			}		
			else{
				logger.info("Succeed to cancel entrust when fail to freezon");
			}
		}
	}
	
	
	
	public C2cEntrustListRes queryEntrustList(String req){	
		C2cEntrustListReq c2cEntrustListReq = JSON.parseObject(req, C2cEntrustListReq.class);
		
		String coin = c2cEntrustListReq.getCoinName();
		List<C2cEntrustModel> sellC2cEntrustModels = c2cEntrustDao.findSellByCoin(coin);
		List<C2cEntrustModel> buyC2cEntrustModels = c2cEntrustDao.findBuyByCoin(coin);		
		C2cEntrustListRes entrustListRes = new C2cEntrustListRes();
		entrustListRes.setBuy(buyC2cEntrustModels);
		entrustListRes.setSell(sellC2cEntrustModels);
		return entrustListRes;
	}
	
	public C2cUserEntrustListRes queryUserEntrust(String req){
		C2cEntrustListReq c2cEntrustListReq = JSON.parseObject(req, C2cEntrustListReq.class);
		
		C2cEntrustModel c2cEntrustModel = new C2cEntrustModel();
		c2cEntrustModel.setUserId(c2cEntrustListReq.getUserId());
		c2cEntrustModel.setCoinName(c2cEntrustListReq.getCoinName());
		List<Map<String,Object>> c2cEntrustModels = c2cEntrustDao.findByCoinAndUserId(c2cEntrustModel);
		
		C2cUserEntrustListRes c2cUserEntrustListRes = new C2cUserEntrustListRes();
		c2cUserEntrustListRes.setOrderList(c2cEntrustModels);
		return c2cUserEntrustListRes;
	}
	
}
