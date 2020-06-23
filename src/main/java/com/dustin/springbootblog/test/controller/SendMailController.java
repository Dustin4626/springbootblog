package com.dustin.springbootblog.test.controller;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SendMailController {

//	set init in application.properties or java config (WebConfig.java)
//	@Autowired
	public JavaMailSender emailSender;

	@GetMapping("mail")
	@ResponseBody
	public void sendSimpleMessage() {
		 SimpleMailMessage message = new SimpleMailMessage();
		 message.setTo("dustin@");
		 message.setSubject("test");
		 message.setText("test 寄送發票");
		 message.setFrom("dustin@");
		 emailSender.send(message);
	}

	@GetMapping("mailWithAttachement")
	@ResponseBody
	public void sendMailWithAttachement() throws MessagingException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo("dustin@");
		helper.setSubject("test");
		helper.setText("test 寄送發票");
		helper.setFrom("EINVNoticeTest<noticeTest@>");
		FileSystemResource file = new FileSystemResource(new File("C:\\juvcfile\\download_bak\\20200620\\SMSErrorNotice_20200620104306.xlsx"));
		helper.addAttachment("SMSErrorNotice_20200620104306.xlsx", file);
		emailSender.send(message);
	}
}
