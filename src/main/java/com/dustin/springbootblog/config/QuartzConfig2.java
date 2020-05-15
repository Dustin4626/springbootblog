package com.dustin.springbootblog.config;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dustin.springbootblog.schedule.QuartzSpringJob2;

/**
 * spring Quartz 配置類
 *
 */
//@Configuration
public class QuartzConfig2 {

	@Bean
	public JobDetail simpleJobDetail() {
		return JobBuilder.newJob(QuartzSpringJob2.class)
				.withIdentity("quartzTaskJob").storeDurably().build();
	}

	@Bean
	public Trigger simpleJobTrigger() {
		SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
				.withIntervalInSeconds(5).repeatForever();
		return TriggerBuilder.newTrigger().forJob(simpleJobDetail())
				.withIdentity("quartzTaskJobTrigger").withSchedule(simpleScheduleBuilder).build();
	}

}
