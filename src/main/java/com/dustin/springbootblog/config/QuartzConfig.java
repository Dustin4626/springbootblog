package com.dustin.springbootblog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.dustin.springbootblog.schedule.QuartzSpringJob;
import com.dustin.springbootblog.schedule.QuartzSpringJob2;

/**
 * spring Quartz 配置類
 *
 */
@Configuration
public class QuartzConfig {

	@Autowired
	MyAdaptableJobFactory myAdaptableJobFactory;
	
	// 1.創建Job對象
	@Bean
	public JobDetailFactoryBean jobDetailFactoryBean() {
		JobDetailFactoryBean factory = new JobDetailFactoryBean();
		// 關聯自己的Job類
		factory.setJobClass(QuartzSpringJob2.class);
		
		return factory;
	}

//	// 2.創建簡單的Trigger對象
//	@Bean
//	public SimpleTriggerFactoryBean simpleTriggerFactoryBean(JobDetailFactoryBean jobDetailFactoryBean) {
//		SimpleTriggerFactoryBean factory = new SimpleTriggerFactoryBean();
//
//		// 關聯jobDetail對象
//		factory.setJobDetail(jobDetailFactoryBean.getObject());
//
//		// 該參數表示一個執行的毫秒數
//		factory.setRepeatInterval(2000);
//
//		// 設置重複次數
//		factory.setRepeatCount(5);
//		return factory;
//	}
	
	// 2.創建cron Trigger對象
	@Bean
	public CronTriggerFactoryBean cronTriggerFactoryBean(JobDetailFactoryBean jobDetailFactoryBean) {
		CronTriggerFactoryBean factory = new CronTriggerFactoryBean();

		// 關聯jobDetail對象
		factory.setJobDetail(jobDetailFactoryBean.getObject());

		// 該參數表示cron表達式
		factory.setCronExpression("0/2 * * * * ?");

		return factory;
	}

	/**
	 * 3.創建schedule對象
	 * 簡單的Trigger對象
	 */
//	@Bean
//	public SchedulerFactoryBean schedulerFactoryBean(SimpleTriggerFactoryBean simpleTriggerFactoryBean) {
//		SchedulerFactoryBean factory = new SchedulerFactoryBean();
//		
//		//關聯Trigger
//		factory.setTriggers(simpleTriggerFactoryBean.getObject());
//		
//		return factory;
//	}
	
	/**
	 *  3.創建schedule對象
	 *  cron Trigger對象
	 * @param cronTriggerFactoryBean
	 * @return
	 */
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean(CronTriggerFactoryBean cronTriggerFactoryBean) {
		SchedulerFactoryBean factory = new SchedulerFactoryBean();
		factory.setJobFactory(myAdaptableJobFactory);
		// 關聯Trigger
		factory.setTriggers(cronTriggerFactoryBean.getObject());
		return factory;
	}
	
}
