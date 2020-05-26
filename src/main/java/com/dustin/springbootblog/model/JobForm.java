package com.dustin.springbootblog.model;

import javax.validation.constraints.NotBlank;

public class JobForm {

	/**
	 * 定時任務全類名稱
	 */
	@NotBlank(message = "類名不能為空")
	private String jobClassName;
	
	/**
	 * 任務組名
	 */
	@NotBlank(message = "任務組名不能為空")
	private String jobGroupName;
	
	/**
	 * 定時任務cron表達式
	 */
	@NotBlank(message = "cron表達式不能為空")
	private String cronExpression;

	public String getJobClassName() {
		return jobClassName;
	}

	public void setJobClassName(String jobClassName) {
		this.jobClassName = jobClassName;
	}

	public String getJobGroupName() {
		return jobGroupName;
	}

	public void setJobGroupName(String jobGroupName) {
		this.jobGroupName = jobGroupName;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public JobForm(@NotBlank(message = "類名不能為空") String jobClassName, @NotBlank(message = "任務組名不能為空") String jobGroupName, @NotBlank(message = "cron表達式不能為空") String cronExpression) {
		super();
		this.jobClassName = jobClassName;
		this.jobGroupName = jobGroupName;
		this.cronExpression = cronExpression;
	}

	public JobForm() {
		super();
	}
	
	
}
