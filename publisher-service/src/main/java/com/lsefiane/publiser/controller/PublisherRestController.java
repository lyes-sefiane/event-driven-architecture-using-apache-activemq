package com.lsefiane.publiser.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lsefiane.common.entities.Message;
import com.lsefiane.publiser.dto.MessageDto;
import com.lsefiane.publiser.service.PublisherService;

/**
 * 
 * PublisherRestController.java
 *
 * @author Lyes Sefiane
 * @email lyes.sefiane@gmail.com
 * @date Jun. 27, 2021
 *
 */
@RestController
@RequestMapping("/publisher/")
public class PublisherRestController {
	
	@Autowired
	private PublisherService publisherService;
	
	@PostMapping("publish/virtual-topic")
	public Message publishToVirtualTopic(@RequestBody @Valid MessageDto messageDto) {
		return publisherService.sendMessageToVirtualTopic(messageDto);
	}
	
	@PostMapping("publish/queue")
	public Message publishToQueue(@RequestBody @Valid MessageDto messageDto) {
		return publisherService.sendMessageToQueue(messageDto);
	}
	
	@PostMapping("publish/topic")
	public Message publishToTopic(@RequestBody @Valid MessageDto messageDto) {
		return publisherService.sendMessageToTopic(messageDto);
	}

}