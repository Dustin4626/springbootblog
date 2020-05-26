package com.dustin.springbootblog.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SYS_LOG_SCHEDULER_FIRED database table.
 * 
 */
@Entity
@Table(name="SYS_LOG_SCHEDULER_FIRED")
@NamedQuery(name="SysLogSchedulerFired.findAll", query="SELECT s FROM SysLogSchedulerFired s")
public class SysLogSchedulerFired implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Temporal(TemporalType.DATE)
	@Column(name="FIRED_DATE")
	private Date firedDate;

	@Column(name="FIRED_DESC")
	private String firedDesc;

	@Column(name="FIRED_STATUS")
	private String firedStatus;

	@Column(name="JOB_ID")
	private String jobId;

	@Column(name="JOB_NAME")
	private String jobName;

	private String remark;

	@Column(name="UUID")
	private String uuid;

	@Column(name="VERSION")
	private BigDecimal version;

	public SysLogSchedulerFired() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getFiredDate() {
		return this.firedDate;
	}

	public void setFiredDate(Date firedDate) {
		this.firedDate = firedDate;
	}

	public String getFiredDesc() {
		return this.firedDesc;
	}

	public void setFiredDesc(String firedDesc) {
		this.firedDesc = firedDesc;
	}

	public String getFiredStatus() {
		return this.firedStatus;
	}

	public void setFiredStatus(String firedStatus) {
		this.firedStatus = firedStatus;
	}

	public String getJobId() {
		return this.jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return this.jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public BigDecimal getVersion() {
		return this.version;
	}

	public void setVersion(BigDecimal version) {
		this.version = version;
	}

}