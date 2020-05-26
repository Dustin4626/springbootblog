package com.dustin.springbootblog.model;

/**
 * QuartzCron entity. @author MyEclipse Persistence Tools
 */
public class QuartzCron implements java.io.Serializable {
	private String startHH;
	private String endHH;
	private String startMM;
	private String endMM;
	private String interval;

	public QuartzCron() {
	}

	public String getStartHH() {
		return startHH;
	}

	public void setStartHH(String startHH) {
		this.startHH = startHH;
	}

	public String getEndHH() {
		return endHH;
	}

	public void setEndHH(String endHH) {
		this.endHH = endHH;
	}

	public String getStartMM() {
		return startMM;
	}

	public void setStartMM(String startMM) {
		this.startMM = startMM;
	}

	public String getEndMM() {
		return endMM;
	}

	public void setEndMM(String endMM) {
		this.endMM = endMM;
	}

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}
}
