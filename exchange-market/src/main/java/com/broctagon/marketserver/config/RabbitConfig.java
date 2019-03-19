package com.broctagon.marketserver.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.broctagon.marketserver.constant.RabbitProperties;


@Configuration
public class RabbitConfig {

	@Autowired
	private RabbitProperties rabbitProperties;

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	public ConnectionFactory rabbitConnectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setHost(rabbitProperties.getHost());
		connectionFactory.setPort(rabbitProperties.getPort());
		connectionFactory.setVirtualHost(rabbitProperties.getVhost());
		connectionFactory.setUsername(rabbitProperties.getUsername());
		connectionFactory.setPassword(rabbitProperties.getPassword());
		connectionFactory.setRequestedHeartBeat(0);
		connectionFactory.setChannelCacheSize(1);
		connectionFactory.setPublisherConfirms(false);
		connectionFactory.setPublisherReturns(false);
		return connectionFactory;
	}

	@Bean
	public TopicExchange nodeExchange() {
		return new TopicExchange(rabbitProperties.getExchange(), false, false);
	}

	@Bean
	public Queue historicalMarketQueue(){
		return new Queue(rabbitProperties.getHistoricalMarketQueue().get("name"), false, false, true);
	}
	
	@Bean
	public Binding historicalMarketBinding() {
		return BindingBuilder.bind(historicalMarketQueue()).to(nodeExchange()).with(rabbitProperties.getHistoricalMarketQueue().get("routingKey"));
	}
	
	@Bean
	public Queue dayMarketQueue(){
		return new Queue(rabbitProperties.getDayMarketQueue().get("name"), false, false, true);
	}
	
	@Bean
	public Binding dayMarketBinding() {
		return BindingBuilder.bind(dayMarketQueue()).to(nodeExchange()).with(rabbitProperties.getDayMarketQueue().get("routingKey"));
	}
	
	@Bean
	public RabbitAdmin rabbitAdmin() {
		RabbitAdmin rabbitAdmin = new RabbitAdmin(rabbitConnectionFactory());
		rabbitAdmin.declareQueue();
		rabbitAdmin.setAutoStartup(true);
		return rabbitAdmin;
	}

	@Bean
	public RabbitTemplate rabbitTemplate() {
		RabbitTemplate rabbitTemplate = rabbitAdmin().getRabbitTemplate();
		rabbitTemplate.setExchange(rabbitProperties.getExchange());
		return rabbitTemplate;
	}

}
