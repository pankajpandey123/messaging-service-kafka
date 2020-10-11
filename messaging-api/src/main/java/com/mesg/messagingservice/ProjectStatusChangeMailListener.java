package com.mesg.messagingservice;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor

public class ProjectStatusChangeMailListener {
	@Autowired
	private EmailService emailService;
	@Autowired
	private TemplateService templateService;

	@KafkaListener(topicPattern = "${kafka.topics.project-status-changed}", autoStartup = "${kafka.enabled}")
	private void listenProjectChange(ConsumerRecord<String, ProjectStatusDto> record) {
		log.info("request for project status change" + record.toString());
		ProjectStatusDto mailData = record.value();
		if (mailData.getAuthorEmailAdres() == null) {
			log.warn("email not provided for author" + record.toString());
		}
		try {
			emailService.sendMail(mailData.getAuthorEmailAdres(), "do mail",
					templateService.generateProjectStatusChangeMail(mailData));
		} catch (MailException e) {
			log.error("email cant be send exception" + e);
		}
	}

}
