package com.dustin.springbootblog.model;

import javax.validation.constraints.NotBlank;

public class JobForm {

	/**
	 * 定時任務全類名稱
	 */
	@NotBlank(message = "類名不能為空")
	private String jobClassname;
	
	/**
	 * 任務組名
	 */
//	@NotBlank(message = "任務組名不能為空")
	private String jobGroupName;
	
	/**
	 * 定時任務cron表達式
	 */
	@NotBlank(message = "cron表達式不能為空")
	private String quartzCron;

	

	public String getJobGroupName() {
		return jobGroupName;
	}


	public JobForm(@NotBlank(message = "類名不能為空") String jobClassname, @NotBlank(message = "任務組名不能為空") String jobGroupName, @NotBlank(message = "cron表達式不能為空") String quartzCron) {
		super();
		this.jobClassname = jobClassname;
		this.jobGroupName = jobGroupName;
		this.quartzCron = quartzCron;
	}

	public JobForm() {
		super();
	}


	public String getJobClassname() {
		return jobClassname;
	}


	public void setJobClassname(String jobClassname) {
		this.jobClassname = jobClassname;
	}


	public String getQuartzCron() {
		return quartzCron;
	}


	public void setQuartzCron(String quartzCron) {
		this.quartzCron = quartzCron;
	}
	
	
}
