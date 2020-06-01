package com.dustin.springbootblog.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dustin.springbootblog.model.JobForm;
import com.dustin.springbootblog.model.QuartzCron;
import com.dustin.springbootblog.model.SysScheduler;
import com.dustin.springbootblog.model.SysSchedulerFiredList;

public interface JobService {

//	/**
//	 * 添加並啟動定時任務
//	 * @param form
//	 * @throws Exception
//	 */
//	void addJob(JobForm form) throws Exception;
	void addJob(SysScheduler job) throws Exception;
	void startJob(String jobId) throws Exception;
	
	/**
	 * 查詢定時任務列表
	 * @param currentPage 當前頁
	 * @param pageSize 每頁條數
	 * @return
	 */
	Page<SysSchedulerFiredList> list(Pageable pageable) throws Exception;
	Page<SysSchedulerFiredList> jobIndexListAll(Pageable pageable) throws Exception;
	List<SysScheduler> listAll();
	Page<SysScheduler> listAll(Pageable pageable);

	QuartzCron getQuartzCronContent(String cron);
	
	void deleteJob(JobForm form);

	
}
