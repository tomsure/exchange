
package com.broctagon.exchangeadmin.constant;


/**
* @auther: Water
* @time: 27 Feb 2018 17:42:42
* 
*/

public class RabbitmqConstants {

	public final static String MQ_KEY_C2C_REQ = "key.c2c.req";
	public final static String MQ_KEY_C2C_RES = "key.c2c.res.";	
	public final static String MQ_QUEUE_C2C_REQ = "queue.c2c.req";	
		
	public final static int TAG_ENTRUST_REQ 	     	 =   0x5001;
	public final static int TAG_ENTRUST_RES 	     	 =   TAG_ENTRUST_REQ + 1;
	public final static int TAG_ENTRUST_CANCEL_REQ   	 =   0x5103;
	public final static int TAG_ENTRUST_CANCEL_RES   	 =   TAG_ENTRUST_CANCEL_REQ + 1;
	public final static int TAG_QUERY_ENTRUST_LIST_REQ   =   0x5003;
	public final static int TAG_QUERY_ENTRUST_LIST_RES   =   TAG_QUERY_ENTRUST_LIST_REQ + 1;
	
	public final static int TAG_QUERY_USER_ENTRUST_LIST_REQ   =   0x5005;
	public final static int TAG_QUERY_USER_CURRENT_TRADE_REQ   =   0x5109;	
	public final static int TAG_QUERY_USER_HISTORYTRADE_LIST_REQ   =   0x5007;
	
	public final static int TAG_TRADE_REQ = 0x5125;
	public final static int TAG_TRADE_RES = TAG_TRADE_REQ + 1;
	public final static int TAG_TRADE_CANCEL_REQ = 0x5127;
	public final static int TAG_TRADE_CANCEL_RES = TAG_TRADE_CANCEL_REQ + 1;
	public final static int TAG_TRADE_CONFORM_PAY_REQ = 0x5009;
	public final static int TAG_TRADE_CONFORM_PAY_RES = TAG_TRADE_CONFORM_PAY_REQ + 1;
	public final static int TAG_TRADE_CONFORM_RECEIVE_REQ = 0x5209;
	public final static int TAG_TRADE_CONFORM_RECEIVE_RES = TAG_TRADE_CONFORM_RECEIVE_REQ + 1;
	public final static int TAG_TRADE_MARKET_REQ = 0x5013;

	public final static int TAG_TRADE_HISTORYMARKET_REQ = 0x5123;
	public final static int TAG_TRADE_HISTORYMARKET_RES = TAG_TRADE_HISTORYMARKET_REQ + 1;
	
	public final static int TAG_USER_ASSETS_REQ = 20987; //20987;//查询用户资产
	
	public final static int TAG_TRADING_STATUS_REQ = 0x5500;//查询单据交易状态 21760
	
	public final static int TAG_COIN_LIST_REQ = 0x5011;
	public final static int TAG_COIN_LIST_RES = TAG_COIN_LIST_REQ + 1;
	
	public final static String MQ_KEY_MANAGER_REQ = "manger_bkey";
	public final static String MQ_KEY_MANAGER_RES = "key_c2c_manager_res";
	public final static String MQ_QUEUE_MANAGER_RES = "queue.c2c_manager.req";
		
	public final static int TAG_MANAGER_FREEZE_REQ = 0x3003;	
	public final static int TAG_MANAGER_FREEZE_RES = 0x3004;
	public final static int TAG_MANAGER_UNFREEZE_REQ = 0x4005;	//16389
	public final static int TAG_MANAGER_C2C_DEAL_REQ = 0x3009;
	
}
