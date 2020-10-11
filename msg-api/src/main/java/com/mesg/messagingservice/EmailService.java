package com.mesg.messagingservice;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {
	@Autowired
	private Environment environment;
	
	/*
	 * @Value("${spring.mail.addresses.from}") private String fromEmailAdr;
	 * 
	 * @Value("${spring.mail.addresses.replyTo}") private String replyToEmailAdr;
	 */
	/*
	 * @Autowired private JavaMailSender mailSender;
	 */ 
	@Bean
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost(environment.getProperty("spring.mail.host"));
	    //mailSender.setPort(Integer.parseInt(environment.getProperty("spring.mail.port")));
	      
	  /*  mailSender.setUsername("my.gmail@gmail.com");
	    mailSender.setPassword("password");*/
	      
	    Properties props = mailSender.getJavaMailProperties();
	   /* props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");*/
	    //props.put("mail.debug", "true");
	      
	    return mailSender;
	}
	public  void sendMail(String recipient,String subject,EmailContentDto content) throws MailException{
		MimeMessagePreparator messagePreparator=mimeMessage->{
			MimeMessageHelper helper=new MimeMessageHelper(mimeMessage, true);
			helper.setFrom(environment.getProperty("spring.mail.addresses.from"));
			helper.setSubject(subject);
			helper.setReplyTo(environment.getProperty("spring.mail.addresses.from"));
			helper.setTo(recipient);
			helper.setText(content.getText(),content.getHtml());
		};
		getJavaMailSender().send(messagePreparator);
	}
}
