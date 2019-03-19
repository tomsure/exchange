package com.broctagon.exchangeadmin.config;

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
import org.springframework.core.env.Environment;


@Configuration
public class RabbitConfig {
	
	
	@Autowired
	private Environment env;

	
	/**
	 * 连接RabbitMQ 配置了连接mq的一些属性 账号密码ip地址等
	 * @return
	 */
	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	public ConnectionFactory rabbitConnectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setHost(env.getProperty("mq.host"));//解析application.yml文件属性    mq连接ip地址（192.168.1.109）
		connectionFactory.setPort(Integer.parseInt(env.getProperty("mq.port")));//连接端口（5672）
		connectionFactory.setUsername(env.getProperty("mq.username"));//mq账号（lsq984）
		connectionFactory.setPassword(env.getProperty("mq.password"));//mq密码（123456789）
		connectionFactory.setVirtualHost(env.getProperty("mq.vhost"));///虚拟主机名（ExchangeVH_v2）
		connectionFactory.setRequestedHeartBeat(0);
		connectionFactory.setChannelCacheSize(1);
		connectionFactory.setPublisherConfirms(false);
		connectionFactory.setPublisherReturns(false);
		return connectionFactory;
	}
	
	
	/**
	 * 创建交换机  创建一个名为“t_node_grp”的交换机
	 * @return
	 */
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(env.getProperty("mq.exchange"), false, false);//t_node_grp
	}
	
	
	
	/**
	 * 创建RabbitAdmin代理类
	 * @return
	 */
	@Bean
	public RabbitAdmin rabbitAdmin() {
		RabbitAdmin rabbitAdmin = new RabbitAdmin(rabbitConnectionFactory());
		rabbitAdmin.declareQueue();
		rabbitAdmin.setAutoStartup(true);
		return rabbitAdmin;
	}

	
	
	/**
	 * 创建rabbitTemplate 消息模板类 
	 * @return
	 */
	@Bean
	public RabbitTemplate rabbitTemplate() {
		RabbitTemplate rabbitTemplate = rabbitAdmin().getRabbitTemplate();
		rabbitTemplate.setExchange(env.getProperty("mq.exchange"));
		return rabbitTemplate;
	}
	
	
	

	
}
