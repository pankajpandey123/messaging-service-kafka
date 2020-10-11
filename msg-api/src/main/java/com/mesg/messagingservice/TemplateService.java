package com.mesg.messagingservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TemplateService {
	@Autowired
	private SpringTemplateEngine templateEngine;
	public EmailContentDto generateProjectStatusChangeMail(ProjectStatusDto projectStatusDto){
		Context context=new Context();
		context.setVariable("fullName", projectStatusDto.getAuthorName());
		context.setVariable("productName", projectStatusDto.getProductName());
		
		return EmailContentDto.builder().text(templateEngine.process("project-status-change.txt", context)).html(templateEngine.process("project-status-change.html", context)).build();
		
	}
}
