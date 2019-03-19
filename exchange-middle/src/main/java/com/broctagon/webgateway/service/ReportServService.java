package com.broctagon.webgateway.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.broctagon.webgateway.constant.Constant;
import com.broctagon.webgateway.model.reportserv.AssetsByTokenRequest;
import com.broctagon.webgateway.model.reportserv.AssetsByUserIdRequest;
import com.broctagon.webgateway.model.reportserv.HistoricalBarRequest;
import com.broctagon.webgateway.model.reportserv.HistoricalOrdersByUserIdRequest;
import com.broctagon.webgateway.model.reportserv.MarketInfoByTokenRequest;
import com.broctagon.webgateway.model.reportserv.MarketSummaryRequest;
import com.broctagon.webgateway.model.reportserv.PendingOrdersByTokenRequest;
import com.broctagon.webgateway.model.reportserv.PendingOrdersByUserIdRequest;
import com.broctagon.webgateway.model.reportserv.TokensRequest;


@Service
public class ReportServService {
	
	// routingKey = Web2ReportSrv
	@Value("${mq.routingKey.reportServ}")
	private String routingKey;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	/**
	 * new业务对象(TokensRequest)，对象json化，发送mq消息
	 * @param wsId
	 * @param sessionId
	 * @param requestId
	 */
	public void getTradeTokens(int wsId, String sessionId, String requestId) {
		TokensRequest tokensRequest = new TokensRequest(wsId, sessionId, requestId);
		System.err.println("TokensRequest对象,查询币币交易货币请求,Tag=24611:"+Constant.GSON.toJson(tokensRequest));
		rabbitTemplate.convertAndSend(routingKey, Constant.GSON.toJson(tokensRequest));
	}

	public void getPendingOrdersByUserId(int wsId, String sessionId, String requestId, int userId) {
		PendingOrdersByUserIdRequest pendingOrdersByUserIdRequest = new PendingOrdersByUserIdRequest();
		pendingOrdersByUserIdRequest.setWSID(wsId);
		pendingOrdersByUserIdRequest.setSessionID(sessionId);
		pendingOrdersByUserIdRequest.setRequestID(requestId);
		pendingOrdersByUserIdRequest.setUserID(userId);
		pendingOrdersByUserIdRequest.setSymbol("All");
		System.err.println("PendingOrdersByUserIdRequest对象,用户挂单信息请求,Tag=24579:"+Constant.GSON.toJson(pendingOrdersByUserIdRequest));
		rabbitTemplate.convertAndSend(routingKey, Constant.GSON.toJson(pendingOrdersByUserIdRequest));
	}

	public void getHistroicalOrdersByUserId(int wsId, String token, String sessionId, String requestId, int userId) {
		HistoricalOrdersByUserIdRequest historicalOrdersByUserIdRequest = new HistoricalOrdersByUserIdRequest();
		historicalOrdersByUserIdRequest.setWSID(wsId);
		historicalOrdersByUserIdRequest.setSessionID(sessionId);
		historicalOrdersByUserIdRequest.setRequestID(requestId);
		historicalOrdersByUserIdRequest.setUserID(userId);
		historicalOrdersByUserIdRequest.setSymbol(token);
		System.err.println("HistoricalOrdersByUserIdRequest对象,查询历史订单,Tag=24577:"+Constant.GSON.toJson(historicalOrdersByUserIdRequest));
		rabbitTemplate.convertAndSend(routingKey, Constant.GSON.toJson(historicalOrdersByUserIdRequest));
	}

	public void getMarketInfoByToken(int wsId, String sessionId, String requestId, String token) {
		MarketInfoByTokenRequest marketInfoByTokenRequest = new MarketInfoByTokenRequest(wsId, sessionId, requestId, token);
		System.err.println("MarketInfoByTokenRequest对象,查询涨跌幅,Tag=24583:"+Constant.GSON.toJson(marketInfoByTokenRequest));
		rabbitTemplate.convertAndSend(routingKey, Constant.GSON.toJson(marketInfoByTokenRequest));
	}

	public void getHistoricalBarsByToken(int wsId, String sessionId, String requestId, String token, String timeframe) {
		HistoricalBarRequest historicalBarRequest = new HistoricalBarRequest(wsId, sessionId, requestId, token, timeframe);
		System.err.println("HistoricalBarRequest对象,查询历史蜡烛条,Tag=24601:"+Constant.GSON.toJson(historicalBarRequest));
		rabbitTemplate.convertAndSend(routingKey, Constant.GSON.toJson(historicalBarRequest));
	}

	/**
	 * new业务对象(PendingOrdersByTokenRequest)，对象json化，发送mq消息
	 * @param wsId
	 * @param sessionId
	 * @param requestId
	 */
	public void getPendingOrdersByToken(int wsId, String sessionId, String requestId, String token) {
		PendingOrdersByTokenRequest pendingOrdersByTokenRequest = new PendingOrdersByTokenRequest(wsId, sessionId, requestId, token);
		System.err.println("PendingOrdersByTokenRequest对象,查询订单簿请求,Tag=393232:"+Constant.GSON.toJson(pendingOrdersByTokenRequest));
		rabbitTemplate.convertAndSend(routingKey, Constant.GSON.toJson(pendingOrdersByTokenRequest));
	}

	public void getMarketSummary(int wsId, String sessionId, String requestId, String token) {
		MarketSummaryRequest marketSummaryRequest = new MarketSummaryRequest(requestId, sessionId, token, wsId);
		System.err.println("MarketSummaryRequest对象,查询某个货币两日内行情请求,Tag=24593:"+Constant.GSON.toJson(marketSummaryRequest));
		rabbitTemplate.convertAndSend(routingKey, Constant.GSON.toJson(marketSummaryRequest));
	}

	public void getAssetsByUserId(int wsId, String sessionId, String requestId, int userId) {
		AssetsByUserIdRequest assetsByUserIdRequest = new AssetsByUserIdRequest(requestId, sessionId, userId, wsId);
		System.err.println("AssetsByUserIdRequest对象,查询用户资产请求,Tag=24581:"+Constant.GSON.toJson(assetsByUserIdRequest));
		rabbitTemplate.convertAndSend(routingKey, Constant.GSON.toJson(assetsByUserIdRequest));
	}
	
	public void getAssetsByToken(int wsId, String sessionId, String requestId, int userId, String baseToken, String transToken) {
		AssetsByTokenRequest assetsByTokenRequest = new AssetsByTokenRequest();
		assetsByTokenRequest.setWSID(wsId);
		assetsByTokenRequest.setSessionID(sessionId);
		assetsByTokenRequest.setRequestID(requestId);
		assetsByTokenRequest.setUserID(userId);
		assetsByTokenRequest.setBaseCoin(baseToken);
		assetsByTokenRequest.setTradeCoin(transToken);
		System.err.println("AssetsByTokenRequest对象,查询用户某种货币余额请求,Tag=24595:"+Constant.GSON.toJson(assetsByTokenRequest));
		rabbitTemplate.convertAndSend(routingKey, Constant.GSON.toJson(assetsByTokenRequest));
	}
	
	
	public void sendMessageToReportServ(String message) {
		System.err.println(message);
		rabbitTemplate.convertAndSend(routingKey, message);
	}
	
}
