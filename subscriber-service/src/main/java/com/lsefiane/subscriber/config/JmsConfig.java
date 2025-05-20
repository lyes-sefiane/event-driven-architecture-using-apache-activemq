package com.lsefiane.subscriber.config;

import jakarta.jms.ConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * JmsConfig.java
 *
 * @author Lyes Sefiane
 * @email lyes.sefiane@gmail.com
 * @date Jun. 26, 2021
 *
 */
@Configuration
@EnableJms
@EnableTransactionManagement
@Slf4j
public class JmsConfig {

	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	public JmsListenerContainerFactory<?> jmsListenerContainerQueue() {
		var factory = new DefaultJmsListenerContainerFactory();
		factory.setMessageConverter(jacksonJmsMessageConverter());
		factory.setConnectionFactory(connectionFactory());
		factory.setTransactionManager(jmsTransactionManager());
		factory.setErrorHandler(
				t -> log.error("Exception Occurs While Handling Received Message : {}", t.getCause().getMessage()));
		return factory;
	}
	
	@Bean
	public MessageConverter jacksonJmsMessageConverter() {
		var converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}

	@Bean
	public PlatformTransactionManager jmsTransactionManager() {
		return new JmsTransactionManager(connectionFactory());
	}

	private ConnectionFactory connectionFactory() {
		return applicationContext.getBean(ConnectionFactory.class);
	}

}
