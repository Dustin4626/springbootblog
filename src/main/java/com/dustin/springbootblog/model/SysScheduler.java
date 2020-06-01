package com.dustin.springbootblog.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SYS_SCHEDULER database table.
 * 
 */
@Entity
@Table(name="SYS_SCHEDULER")
@NamedQuery(name="SysScheduler.findAll", query="SELECT s FROM SysScheduler s")
public class SysScheduler implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Temporal(TemporalType.DATE)
	@Column(name="EXPIRE_DATE")
	private Date expireDate;

	private String flag;

	@NotBlank(message = "類名不能為空")
	@Column(name="JOB_CLASSNAME")
	private String jobClassname;

	@Column(name="JOB_ID")
	private String jobId;

	@NotBlank(message = "類名稱不能為空")
	@Column(name="JOB_NAME")
	private String jobName;

	@Temporal(TemporalType.DATE)
	@Column(name="MODIFY_DATE")
	private Date modifyDate;

//	@NotBlank(message = "cron表達式不能為空")
	@Column(name="QUARTZ_CRON")
	private String quartzCron;

	private String remark;

	@Column(name="RUN_AP")
	private String runAp;

	@Column(name="UUID")
	private String uuid;

	@Column(name="VERSION")
	private BigDecimal version;

	public SysScheduler() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getExpireDate() {
		return this.expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getJobClassname() {
		return this.jobClassname;
	}

	public void setJobClassname(String jobClassname) {
		this.jobClassname = jobClassname;
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

	public Date getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getQuartzCron() {
		return this.quartzCron;
	}

	public void setQuartzCron(String quartzCron) {
		this.quartzCron = quartzCron;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRunAp() {
		return this.runAp;
	}

	public void setRunAp(String runAp) {
		this.runAp = runAp;
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