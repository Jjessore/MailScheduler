package com.emailclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.metrics.ApplicationStartup;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.mail.MessagingException;
import java.util.Date;

@SpringBootApplication
@EnableScheduling
public class EmailClientApplication {

	@Autowired
	private EmailSenderService emailSenderService;
	public static void main(String[] args) {
		SpringApplication.run(EmailClientApplication.class, args);
	}

	//@EventListener(ApplicationReadyEvent.class)
	@Scheduled(cron = "0 00 15 * * *")
	public void startMail() throws MessagingException {
		emailSenderService.sendSimpleMail("kishorekumarmc90s@gmail.com",
											"This is a sample mail sent with CC to Andrews for the spring boot demo, the mail was scheduled at 3.00pm",
											"andrew_india@yahoo.com",
											"Sample mail from spring boot");

		emailSenderService.sendAttachmentMail("kishorekumarmc90s@gmail.com",
												"This is a sample mail sent with CC to Andrews for the spring boot demo, the mail was scheduled at 3.00pm",
												"Sample mail with attachment from spring boot",
												"andrew_india@yahoo.com",
												"D:\\Work\\rfc3881.pdf");

		System.out.println("Schedule task completed");

	}

}
