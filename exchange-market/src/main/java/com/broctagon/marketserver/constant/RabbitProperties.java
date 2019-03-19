package com.broctagon.marketserver.constant;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="mq")
public class RabbitProperties {

	private String host;
	
	private int port;
	
	private String username;

	private String password;
	
	private String vhost;
	
	private String exchange;

	private Map<String, String> historicalMarketQueue;

	private Map<String, String> dayMarketQueue;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVhost() {
		return vhost;
	}

	public void setVhost(String vhost) {
		this.vhost = vhost;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public Map<String, String> getHistoricalMarketQueue() {
		return historicalMarketQueue;
	}

	public void setHistoricalMarketQueue(Map<String, String> historicalMarketQueue) {
		this.historicalMarketQueue = historicalMarketQueue;
	}

	public Map<String, String> getDayMarketQueue() {
		return dayMarketQueue;
	}

	public void setDayMarketQueue(Map<String, String> dayMarketQueue) {
		this.dayMarketQueue = dayMarketQueue;
	}

}
