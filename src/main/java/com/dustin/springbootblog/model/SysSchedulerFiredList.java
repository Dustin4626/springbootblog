package com.dustin.springbootblog.model;

import java.util.Date;

import javax.persistence.Entity;

/**
 * SysSchedulerFiredList entity. @author MyEclipse Persistence Tools
 */

public class SysSchedulerFiredList implements java.io.Serializable {
	
	
	private String jobId;
	private String jobName;
	private String quartzCron;
	private String runAp;
	private String remark;
	private Date firedDate;
	private String firedDesc;
	private String status;
	private String quartzCronText;
	private String cremark;
	private String sfriedDate;

	public SysSchedulerFiredList() {
	}

	public String getJobName() {
		return this.jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getQuartzCron() {
		return this.quartzCron;
	}

	public void setQuartzCron(String quartzCron) {
		this.quartzCron = quartzCron;
	}
	
	public String getRunAp() {
		return this.runAp;
	}

	public void setRunAp(String runAp) {
		this.runAp = runAp;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {		
		this.remark = remark;
	}
	

	public Date getFiredDate() {
		return firedDate;
	}

	public void setFiredDate(Date firedDate) {
		this.firedDate = firedDate;
	}

	public String getFiredDesc() {
		return firedDesc;
	}

	public void setFiredDesc(String firedDesc) {
		this.firedDesc = firedDesc;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getQuartzCronText() {
		return quartzCronText;
	}

	public void setQuartzCronText(String quartzCronText) {
		this.quartzCronText = quartzCronText;
	}

	public String getCremark() {
		return cremark;
	}

	public void setCremark(String cremark) {
		this.cremark = cremark;
	}

	public String getSfriedDate() {
		return sfriedDate;
	}

	public void setSfriedDate(String sfriedDate) {
		this.sfriedDate = sfriedDate;
	}
	
}
