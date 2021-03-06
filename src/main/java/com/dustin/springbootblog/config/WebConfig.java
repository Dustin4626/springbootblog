package com.dustin.springbootblog.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.dustin.springbootblog.interceptor.FirstInterceptor;
import com.dustin.springbootblog.interceptor.SecondInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/index.html").setViewName("/index");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(new LoginInterceptor())
//				.addPathPatterns("/admin/**")
//				.excludePathPatterns("/admin","/admin/login");
		
		/**
		 * 多攔截器範例
		 */
		registry.addInterceptor(new FirstInterceptor())
				.addPathPatterns("/admin/testInterceptor");
		registry.addInterceptor(new SecondInterceptor())
				.addPathPatterns("/admin/upload", "/admin/testInterceptor");
	}

	@Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
       return builder.build();
    }
	
//	@Bean
//	public JavaMailSender getJavaMailSender() {
//	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//	    mailSender.setHost("172.16.9.21");
//	    mailSender.setPort(25);
//	    mailSender.setUsername();
//	    mailSender.setPassword();
//	    
//	    Properties props = mailSender.getJavaMailProperties();
//	    props.put("mail.transport.protocol", "smtp");
//	    props.put("mail.smtp.auth", "false");
//	    props.put("mail.smtp.starttls.enable", "true");
//	    props.put("mail.debug", "true");
//	    return mailSender;
//	}
	
//	@Bean
//	public JavaMailSender getJavaMailSender() {
//	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//	    mailSender.setHost("smtp.gmail.com");
//	    mailSender.setPort(587);
//	    mailSender.setUsername();
//	    mailSender.setPassword();
//	    
//	    Properties props = mailSender.getJavaMailProperties();
//	    props.put("mail.transport.protocol", "smtp");
//	    props.put("mail.smtp.auth", "true");
//	    props.put("mail.smtp.starttls.enable", "true");
//	    props.put("mail.debug", "true");
//	    return mailSender;
//	}
	
}
