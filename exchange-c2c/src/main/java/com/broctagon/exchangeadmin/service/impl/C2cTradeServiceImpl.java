
package com.broctagon.exchangeadmin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.broctagon.exchangeadmin.constant.RabbitmqConstants;
import com.broctagon.exchangeadmin.dao.C2cEntrustDao;
import com.broctagon.exchangeadmin.dao.C2cTradeDao;
import com.broctagon.exchangeadmin.listener.MessageDispatcher;
import com.broctagon.exchangeadmin.message.BaseMsg;
import com.broctagon.exchangeadmin.message.C2cFindReq;
import com.broctagon.exchangeadmin.message.C2cHistoryMarketRes;
import com.broctagon.exchangeadmin.message.C2cResultRes;
import com.broctagon.exchangeadmin.message.C2cTradeDetailRes;
import com.broctagon.exchangeadmin.message.C2cTradeMarketRes;
import com.broctagon.exchangeadmin.message.C2cTradeReq;
import com.broctagon.exchangeadmin.message.C2cUpdateTradeReq;
import com.broctagon.exchangeadmin.message.ManagerTradeReq;
import com.broctagon.exchangeadmin.message.c2cObject;
import com.broctagon.exchangeadmin.model.C2cEntrustModel;
import com.broctagon.exchangeadmin.model.C2cMarketModel;
import com.broctagon.exchangeadmin.model.C2cTradeAccountModel;
import com.broctagon.exchangeadmin.model.C2cTradeDetailModel;
import com.broctagon.exchangeadmin.model.C2cTradeModel;
import com.broctagon.exchangeadmin.model.C2cTradeVolumeModel;
import com.broctagon.exchangeadmin.service.C2cTradeService;
import com.broctagon.exchangeadmin.util.C2cConstants;

/**
* @auther: Water
* @time: 16 Mar 2018 16:44:12
* 
*/

@Service
@Transactional
public class C2cTradeServiceImpl implements C2cTradeService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private C2cEntrustDao c2cEntrustDao;
	
	@Autowired
	private C2cTradeDao c2cTradeDao;
	
	@Autowired
	private C2cTradeService c2cTradeService;
	
	@Autowired
	private MessageDispatcher messageDispatcher;
	
	public C2cResultRes trade(String req){
		C2cTradeReq c2cTradeReq = JSON.parseObject(req, C2cTradeReq.class);
		
		C2cResultRes baseMsg = new C2cResultRes();
		C2cEntrustModel findC2cEntrustModel = c2cEntrustDao.findById(c2cTradeReq.getEntrustId());
		if(findC2cEntrustModel==null || findC2cEntrustModel.getAmount()<c2cTradeReq.getAmount()){
			baseMsg.setResult(false);
			baseMsg.setReason("Entrust balance not enough");
			return baseMsg;
		}
		
		C2cEntrustModel c2cEntrustModel = new C2cEntrustModel();
		c2cEntrustModel.setId(c2cTradeReq.getEntrustId());
		c2cEntrustModel.setAmount(c2cTradeReq.getAmount());
		int entrustResult = c2cEntrustDao.subtractAmount(c2cEntrustModel);
		if(entrustResult>=1){
			C2cTradeModel c2cTradeModel = new C2cTradeModel();
			c2cTradeModel.setEntrustId(c2cTradeReq.getEntrustId());
			c2cTradeModel.setTradeUserId(c2cTradeReq.getUserId());
			c2cTradeModel.setAmount(c2cTradeReq.getAmount());
			c2cTradeModel.setTradeStatus(C2cConstants.ENTRUST_STATUS_PENDING);
			int tradeResult = c2cTradeDao.save(c2cTradeModel);
			
			Map<String,Object> mapData = c2cTradeDao.selectBanklist(findC2cEntrustModel.getUserId());
			mapData.put("orderId", findC2cEntrustModel.getId());
			mapData.put("tradeId", c2cTradeModel.getId());
			System.err.println("成功");
			baseMsg.setResult(true);
			baseMsg.setList(mapData);
			return baseMsg;
		}
		else{			
			baseMsg.setResult(false);
			baseMsg.setReason("Update entrust balance failed!");
			return baseMsg;
		}
	}
		
	public C2cResultRes cancelTrade(String req){
		C2cUpdateTradeReq c2cUpdateTradeReq = JSON.parseObject(req, C2cUpdateTradeReq.class); 
		int tradeId = c2cUpdateTradeReq.getTradeId();
		
		C2cTradeModel tradeData = c2cTradeDao.findById(tradeId);
		
		C2cResultRes baseMsg = new C2cResultRes();
		C2cTradeModel c2cTradeModel = new C2cTradeModel();
		c2cTradeModel.setId(tradeId);
		c2cTradeModel.setTradeStatus(C2cConstants.TRADE_STATUS_CANCEL);
		int result = c2cTradeDao.cancelTrade(c2cTradeModel);
		if(result<1){
			baseMsg.setResult(false);
		}else{
			C2cEntrustModel c2cEntrustModel = new C2cEntrustModel();
			c2cEntrustModel.setId(tradeData.getEntrustId());
			c2cEntrustModel.setAmount(tradeData.getAmount());
			c2cEntrustDao.updateAmount(c2cEntrustModel);
		}	
		return baseMsg;
	} 
	
	public BaseMsg confirmPay(String req){
		C2cResultRes baseMsg = new C2cResultRes();
		
		C2cUpdateTradeReq c2cUpdateTradeReq = JSON.parseObject(req, C2cUpdateTradeReq.class); 
		int tradeId = c2cUpdateTradeReq.getTradeId();
		
		C2cTradeModel c2cTradeModel = new C2cTradeModel();
		c2cTradeModel.setId(tradeId);
		c2cTradeModel.setTradeStatus(C2cConstants.TRADE_STATUS_PAID);
		int updateResult = c2cTradeDao.updateTradeStatus(c2cTradeModel);
		if(updateResult<1){
			baseMsg.setResult(false);
			baseMsg.setReason("Update Trade Status failed");
		}else{
			Map<String,Object> mapData = c2cTradeDao.selectUserId(tradeId);
			baseMsg.setList(mapData);
		}
		
		return baseMsg;
	}
	
	public BaseMsg confirmReceived(String req){
		C2cResultRes baseMsg = new C2cResultRes();
		
		C2cUpdateTradeReq c2cUpdateTradeReq = JSON.parseObject(req, C2cUpdateTradeReq.class); 
		int tradeId = c2cUpdateTradeReq.getTradeId();
		
		C2cTradeModel c2cTradeModel = new C2cTradeModel();
		c2cTradeModel.setId(tradeId);
		c2cTradeModel.setTradeStatus(C2cConstants.TRADE_STATUS_RECEIVED);
		int updateResult = c2cTradeDao.updateTradeStatus(c2cTradeModel);
		if(updateResult<1){
			baseMsg.setResult(false);
			baseMsg.setReason("Update Trade Status failed");
		}
		else{
			// notify the trade confirm to manager
			C2cTradeAccountModel c2cTradeAccountModel = c2cTradeDao.findTradeDetailById(tradeId);				
			logger.info("JSON.toJSONString(c2cTradeAccountModel):" + JSON.toJSONString(c2cTradeAccountModel));
			
			ManagerTradeReq managerTradeReq = new ManagerTradeReq();
			managerTradeReq.setTag(RabbitmqConstants.TAG_MANAGER_C2C_DEAL_REQ);			
			managerTradeReq.setSymbol(c2cTradeAccountModel.getCoinName());
			managerTradeReq.setDealNum(c2cTradeAccountModel.getAmount());
			managerTradeReq.setDealPrice(c2cTradeAccountModel.getPrice());			
			managerTradeReq.setAskOrderID(c2cTradeAccountModel.getTradeid());
			managerTradeReq.setBidOrderID(c2cTradeAccountModel.getTradeid());
			managerTradeReq.setSymbol(c2cTradeAccountModel.getCoinName());
			managerTradeReq.setTransType(c2cTradeAccountModel.getEntrustType());
			managerTradeReq.setTransactionTime(c2cTradeAccountModel.getUpdatetime());
			if(c2cTradeAccountModel.getEntrustType()==C2cConstants.ENTRUST_TYPE_SELL){
				managerTradeReq.setAskUserID(c2cTradeAccountModel.getEntrusterId());
				managerTradeReq.setBidUserID(c2cTradeAccountModel.getTraderId());
			}
			else{
				managerTradeReq.setAskUserID(c2cTradeAccountModel.getTraderId());
				managerTradeReq.setBidUserID(c2cTradeAccountModel.getEntrusterId());
			}
			
			messageDispatcher.sendToManager(JSON.toJSONString(managerTradeReq));
		}
		
		return baseMsg;
	}
	
	public BaseMsg findCurrentTrade(String req){
		C2cFindReq c2cFindReq = JSON.parseObject(req, C2cFindReq.class);
		
		C2cEntrustModel c2cEnturstModel = new C2cEntrustModel();
		c2cEnturstModel.setUserId(c2cFindReq.getUserId());
		c2cEnturstModel.setCoinName(c2cFindReq.getCoinName());
		List<Map<String,Object>> c2cTradeDetailModels = c2cTradeDao.findCurrentTrade(c2cEnturstModel);//
		List<Map<String,Object>> c2cMerchantTradeDetailModels = c2cTradeDao.findCurrentMerchantTrade(c2cEnturstModel);
		System.out.println("JSON.toJSONString(c2cTradeDetailModels):" + JSON.toJSONString(c2cTradeDetailModels));
		System.out.println("JSON.toJSONString(c2cMerchantTradeDetailModels):" + JSON.toJSONString(c2cMerchantTradeDetailModels));
		
		List<Map<String,Object>> tradeCombine = new ArrayList<Map<String,Object>>();
		if(c2cTradeDetailModels!=null && !c2cTradeDetailModels.isEmpty()){
			tradeCombine.addAll(c2cTradeDetailModels);
		}
		if(c2cMerchantTradeDetailModels!=null && !c2cMerchantTradeDetailModels.isEmpty()){
			tradeCombine.addAll(c2cMerchantTradeDetailModels);
		}
		
		C2cTradeDetailRes c2cTradeDetailRes = new C2cTradeDetailRes();
		c2cTradeDetailRes.setOrderList(tradeCombine);
		
		return c2cTradeDetailRes;
	}

	public BaseMsg findHistoryMarket(String req){
		C2cFindReq c2cFindReq = JSON.parseObject(req, C2cFindReq.class);
		
		String coin = c2cFindReq.getCoinName();
		List<Map<String,Object>> historyMarket = c2cTradeDao.findHistoryMarket(coin);
		C2cHistoryMarketRes c2cHistoryMarketRes = new C2cHistoryMarketRes();
		c2cHistoryMarketRes.setHistoryMarket(historyMarket);
		
		return c2cHistoryMarketRes;
	}
	
	public BaseMsg findUserHistoryTrade(String req){
		C2cFindReq c2cFindReq = JSON.parseObject(req, C2cFindReq.class);
		
		C2cEntrustModel c2cEnturstModel = new C2cEntrustModel();
		c2cEnturstModel.setCoinName(c2cFindReq.getCoinName());
		c2cEnturstModel.setUserId(c2cFindReq.getUserId());
		List<Map<String,Object>> historyMarket = c2cTradeDao.findUserHistoryTrade(c2cEnturstModel);	
		List<Map<String,Object>> merchantHistoryMarket = c2cTradeDao.findMerchantHistoryTrade(c2cEnturstModel);	
		
		System.out.println("historyMarket:" + JSON.toJSONString(historyMarket));
		System.out.println("merchantHistoryMarket:" + JSON.toJSONString(merchantHistoryMarket));
		
		List<Map<String,Object>> historyMarketCombine = new ArrayList<Map<String,Object>>();
		if(historyMarket!=null&&!historyMarket.isEmpty()){
			historyMarketCombine.addAll(historyMarket);
		}
		if(merchantHistoryMarket!=null&&!merchantHistoryMarket.isEmpty()){
			historyMarketCombine.addAll(merchantHistoryMarket);
		}
		
		C2cHistoryMarketRes c2cHistoryMarketRes = new C2cHistoryMarketRes();
		c2cHistoryMarketRes.setHistoryMarket(historyMarketCombine);
		
		return c2cHistoryMarketRes;
 	}
	
	public BaseMsg findTradeMarket(String req){
		C2cFindReq c2cFindReq = JSON.parseObject(req, C2cFindReq.class);
		
		C2cTradeMarketRes c2cTradeMarketRes = new C2cTradeMarketRes();
		List<C2cTradeVolumeModel> c2cTradeVolumeModels = c2cTradeDao.findTradeVolume(c2cFindReq.getCoinName());
		for(C2cTradeVolumeModel c2cTradeVolumeModel: c2cTradeVolumeModels){
			if("yesterday".equals(c2cTradeVolumeModel.getTime())){
				c2cTradeMarketRes.setYesterday(c2cTradeVolumeModel);
			}
			else if("today".equals(c2cTradeVolumeModel.getTime())){
				c2cTradeMarketRes.setToday(c2cTradeVolumeModel);
			}
		}
		
		Double price = c2cTradeDao.findLastPrice(c2cFindReq.getCoinName());
		if(price!=null){
			c2cTradeMarketRes.setCurrentPrice(price);
		}
				
		return c2cTradeMarketRes;
	}

	@Override
	public BaseMsg selUserAsset(String req) {
		c2cObject c2cO = new c2cObject();
		Map<String,Object> map = JSON.parseObject(req);
		if(map.get("UserID") != null && map.get("CoinName") != null) {
			Integer userId =  Integer.valueOf(map.get("UserID").toString());
			String coinName = map.get("CoinName").toString();
			System.err.println("userId = " + userId+" coinName = "+coinName);
			Map<String,Object> userAssetData = c2cTradeDao.selectUserAssets(userId,coinName);
			
			
			c2cO.setResData(userAssetData);
		}
		
		return c2cO;
	}
	
}
