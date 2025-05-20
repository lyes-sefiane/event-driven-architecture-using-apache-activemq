package com.lsefiane.publisher.jms.postprocess;

import jakarta.jms.JMSException;
import jakarta.jms.Message;

import org.springframework.jms.core.MessagePostProcessor;

import com.lsefiane.common.enums.MessageStatus;

/**
 * 
 * CustomMessagePostProcessor.java
 *
 * @author Lyes Sefiane
 * @email lyes.sefiane@gmail.com
 * @date Jul. 3, 2021
 *
 */
public class CustomMessagePostProcessor implements MessagePostProcessor {

	private MessageStatus messageStatus;

	public CustomMessagePostProcessor() {
		this.messageStatus = MessageStatus.NEW;
	}

	public CustomMessagePostProcessor(MessageStatus messageStatus) {
		this.messageStatus = messageStatus;
	}

	@Override
	public Message postProcessMessage(Message message) throws JMSException {
		message.setStringProperty("messageStatus", messageStatus.getValue());
		return message;
	}

}
