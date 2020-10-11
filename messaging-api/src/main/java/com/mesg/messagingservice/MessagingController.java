package com.mesg.messagingservice;

import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping
@AllArgsConstructor
@Slf4j
public class MessagingController {

	private KafkaTemplate<String, ProjectStatusDto> kafkaProducer;
	private KafkaProperties properties;

	@PostMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)

	public void sendProjectStatusEmail(@RequestBody ProjectStatusDto pdto) {
		log.info("send project status change email:" + pdto.toString());
		kafkaProducer.send(properties.getTopics().getProjStatusChange(), pdto);
	}

}
