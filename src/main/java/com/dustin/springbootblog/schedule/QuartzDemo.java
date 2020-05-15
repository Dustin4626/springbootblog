package com.dustin.springbootblog.schedule;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *	定義任務類 
 *
 *	implements org.quartz.Job;
 *
 */
public class QuartzDemo implements Job{

	/**
	 * 	任務被觸發時,所執行的方法
	 */
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("任務被觸發:" + new Date());
	}

}
