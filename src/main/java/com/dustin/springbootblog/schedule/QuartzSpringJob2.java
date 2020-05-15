package com.dustin.springbootblog.schedule;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 定義任務類 spring boot control
 *
 * 1.class implements org.quartz.Job;
 *
 * 2.SpringbootblogApplication.java add annotation @EnableScheduling
 *
 * 3. @Configuration QuartzConfig 設置job關聯
 */
public class QuartzSpringJob2 extends QuartzJobBean {

	@Autowired
	QuartzSpringService service;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		System.out.println("QuartzSpringJob2 "+ new Date());
		service.addUser();
	}

}
