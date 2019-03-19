package com.exchange.website.response;

/**
 * 大盤信息
 * @author lj
 *
 */
public class MarketResponse {
	//市场总证券
	private String sum_securities;
	//市场总美元证券
	private String dollar_securities;
	//总vol
	private String sum_vol;
	//总交易
	private String sum_trade;
	public String getSum_securities() {
		return sum_securities;
	}
	public void setSum_securities(String sum_securities) {
		this.sum_securities = sum_securities;
	}
	public String getDollar_securities() {
		return dollar_securities;
	}
	public void setDollar_securities(String dollar_securities) {
		this.dollar_securities = dollar_securities;
	}
	public String getSum_vol() {
		return sum_vol;
	}
	public void setSum_vol(String sum_vol) {
		this.sum_vol = sum_vol;
	}
	public String getSum_trade() {
		return sum_trade;
	}
	public void setSum_trade(String sum_trade) {
		this.sum_trade = sum_trade;
	}
}
