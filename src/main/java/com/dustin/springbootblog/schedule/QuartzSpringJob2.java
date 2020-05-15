package com.dustin.springbootblog.schedule;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 定義任務類 spring boot control
 *
 * 1.class implements org.quartz.Job;
 *
 * 2.SpringbootblogApplication.java add annotation @EnableScheduling
 *
 * 3. @Configuration QuartzConfig 設置job關聯
 */
public class QuartzSpringJob2 implements Job {

	@Autowired
	QuartzSpringService service;

	/**
	 * 任務被觸發時,所執行的方法
	 */
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// System.out.println("spring quartz 任務被觸發:" + new Date());
		System.out.println("QuartzSpringJob2 ");
		service.addUser();
	}

}
