package com.broctagon.webgateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;
import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;

import com.broctagon.webgateway.constant.Constant;
import com.broctagon.webgateway.constant.Path;
import com.broctagon.webgateway.service.PushService;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/websocket").withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker(Path.PREFIX);
		registry.setApplicationDestinationPrefixes("/ws");
		registry.setUserDestinationPrefix("/user");
	}
	
	@Override
    public void configureWebSocketTransport(final WebSocketTransportRegistration registration) {
        registration.addDecoratorFactory(new WebSocketHandlerDecoratorFactory() {
            @Override
            public WebSocketHandler decorate(final WebSocketHandler handler) {
                return new WebSocketHandlerDecorator(handler) {
                	/**
                	 * 创建WebSocket连接
                	 */
                    @Override
                    public void afterConnectionEstablished(final WebSocketSession session) throws Exception {
                    	System.out.println("开启连接!");
                    	super.afterConnectionEstablished(session);
                    }

                    /**
                     * 关闭WebSocket连接时需要执行的方法
                     */
                    @Override
                    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
                    	System.out.println("断开连接!");
                    	PushService.removePushSessionId(session.getId());
                    	PushService.removeSessionId(session.getId());
                    	super.afterConnectionClosed(session, closeStatus);
                    }
                };
            }
        });
        super.configureWebSocketTransport(registration);
    }
	
}
