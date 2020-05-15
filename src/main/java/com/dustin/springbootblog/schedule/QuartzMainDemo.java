package com.dustin.springbootblog.schedule;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * simple java demo 
 * 
 * Quartz no spring control
 *
 */
public class QuartzMainDemo {

	public static void main(String[] args) throws SchedulerException {

		// 1.創建Job對象
		JobDetail jobDetail = JobBuilder.newJob(QuartzDemo.class).build();

		/**
		 * 2.創建Trigger對象
		 * 
		 * 	兩種寫法
		 * 1.簡單trigger觸發時間,通過Quartz提供的方法完成重複調用 
		 * 2.corn表達式
		 */

		// 2.1.簡單trigger觸發時間
//		Trigger trigger = TriggerBuilder.newTrigger()
//				.withSchedule(SimpleScheduleBuilder.repeatSecondlyForever()).build();
		
		// 2.2.corn表達式
		Trigger trigger = TriggerBuilder.newTrigger()
				.withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?")).build();

		// 3.創建schedule對象
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		scheduler.scheduleJob(jobDetail, trigger);

		// 4.啟動
		scheduler.start();
	}

}
