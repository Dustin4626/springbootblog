package com.dustin.springbootblog.web.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dustin.springbootblog.model.JobForm;
import com.dustin.springbootblog.model.QuartzCron;
import com.dustin.springbootblog.model.SysLogSchedulerFired;
import com.dustin.springbootblog.model.SysScheduler;
import com.dustin.springbootblog.model.SysSchedulerFiredList;
import com.dustin.springbootblog.web.dao.JobRepository;

@Service
public class JobServiceImpl extends BaseService<SysScheduler,Long> implements JobService{
	private static final String JOB_START = "ACTIVE";
	private static final String JOB_STOP = "";
	
	private final Scheduler scheduler;
	private final JobRepository dao;
	
	@Autowired
	public JobServiceImpl(Scheduler scheduler, JobRepository dao) {
		super();
		this.scheduler = scheduler;
		this.dao = dao;
	}
	
	@Override
	public Class defaultClass() {
		return SysScheduler.class;
	}

	@Override
	@Transactional
	public void addJob(SysScheduler job) throws Exception {
		SysScheduler dbJob = dao.findByJobId(job.getJobId());
		if(dbJob!=null) {
			
		}
		
		dao.save(job);
		
		// temp add
		GenericService sev = defaultSev(SysLogSchedulerFired.class);
		SysLogSchedulerFired sysLogSchedulerFired = new SysLogSchedulerFired();
		sysLogSchedulerFired.setJobId(job.getJobId());
		sysLogSchedulerFired.setJobName(job.getJobName());
		sev.save(sysLogSchedulerFired);
	}
	
	@Override
	public void startJob(String jobId) throws Exception {
		SysScheduler job = dao.findByJobId(jobId);
		
		Job newJob = (Job)Class.forName(job.getJobClassname()).newInstance();
		
		// 1.創建Job對象
		JobDetail jobDetail = JobBuilder.newJob(newJob.getClass()).build();

		/**
		 * 2.創建Trigger對象
		 * 
		 * 	兩種寫法
		 * 1.簡單trigger觸發時間,通過Quartz提供的方法完成重複調用 
		 * 2.corn表達式
		 */
		
		// corn表達式
		Trigger trigger = TriggerBuilder.newTrigger()
				.withSchedule(CronScheduleBuilder.cronSchedule(job.getQuartzCron())).build();

		// 3.創建schedule對象
		scheduler.scheduleJob(jobDetail, trigger);

		// 4.啟動
		scheduler.start();
		
		job.setRemark(JOB_START);
		dao.save(job);
	}

//	@Override
//	public Page<Map> list(Pageable pageable) {
//		return dao.list(pageable);
//	}

	@Override
	public Page<SysSchedulerFiredList> jobIndexListAll(Pageable pageable) throws Exception {
		GenericService defaultSev = defaultSev(SysSchedulerFiredList.class);
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT A.JOB_ID jobId, A.JOB_NAME jobName, A.RUN_AP runAp, A.QUARTZ_CRON quartzCron, A.REMARK remark ");
		sb.append(" , B.FIRED_DATE firedDate, B.FIRED_DESC firedDesc ");
		sb.append(" FROM SYS_SCHEDULER A ");
		sb.append(" LEFT JOIN SYS_LOG_SCHEDULER_FIRED B ON B.JOB_ID = A.JOB_ID ");
		sb.append(" ,( SELECT  JOB_ID ,max(ID) ID FROM  SYS_LOG_SCHEDULER_FIRED GROUP BY JOB_ID) C ");
		sb.append(" WHERE B.ID IS NULL OR B.ID = C.id AND B.JOB_ID = C.JOB_ID ");
		sb.append(" GROUP BY  A.JOB_ID, A.JOB_NAME, A.RUN_AP, ");
		sb.append(" A.QUARTZ_CRON, A.REMARK, B.FIRED_DATE, ");
		sb.append(" B.FIRED_DESC,A.VERSION  ORDER BY A.VERSION ");
//		return defaultSev.findSQL4Pagin(sb.toString(), pageable);
		Page<SysSchedulerFiredList> page = defaultSev.findSQL4Pagin(sb.toString(), pageable);
		page = page.map(this :: setQuartzCronText);
		return page;
	}
	
	public List<SysScheduler> listAll() {
		return dao.findAll();
	}

//	@Override
//	public Page<SysScheduler> listAll(Pageable pageable) {
//		Page<SysScheduler> page = dao.findAll(pageable);
//		page = page.map(this :: setQuartzCronText);
//		return page;
//	}
	
	private SysSchedulerFiredList setQuartzCronText(final SysSchedulerFiredList sysScheduler) {
		sysScheduler.setQuartzCronText(getPresentationQuartzCron(sysScheduler.getQuartzCron()));
		return sysScheduler;
	}

	
	@Override
	public Page<SysSchedulerFiredList> list(Pageable pageable) {
		Page<Map<String, String>> page = dao.list(pageable);
		Page<SysSchedulerFiredList> dto = page.map(t -> {
			try {
				return setQuartzCronText(t);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		});
		return dto;
	}
	
	private SysSchedulerFiredList setQuartzCronText(Map<String, String> map) throws Exception {
		SysSchedulerFiredList sysSchedulerFiredList = new SysSchedulerFiredList();
		sysSchedulerFiredList.setQuartzCronText(getPresentationQuartzCron(map.get("quartzCron")));
		BeanUtils.populate(sysSchedulerFiredList, map);
		return sysSchedulerFiredList;
	}

	@Override
	public void deleteJob(JobForm form) {
		
	}

	@Override
	public QuartzCron getQuartzCronContent(String cron) {

		String sRtn = "";
		String[] smCron = cron.split(" ");
		QuartzCron tmp = new QuartzCron();
		try {
			// 時
			tmp.setEndHH("0");
			if ("*".equals(smCron[2])) {
				tmp.setStartHH("0");
			} else {
				// 時程內容為區間ex:10-22
				if (smCron[2].indexOf("-") != -1) {
					tmp.setStartHH(smCron[2].substring(0, smCron[2].indexOf("-")));
					tmp.setEndHH(smCron[2].substring((smCron[2].indexOf("-")) + 1));
				} else {
					tmp.setStartHH(smCron[2]);
				}
			}
			// 分
			if ("*".equals(smCron[1])) {
				tmp.setStartMM("0");
				tmp.setEndMM("0");
				tmp.setInterval("0");
			} else {
				if (smCron[1].contains("/")) {
					tmp.setStartMM(smCron[1].substring(0, smCron[1].indexOf("/")));
					tmp.setEndMM("0");
					tmp.setInterval(smCron[1].substring((smCron[1].indexOf("/")) + 1));
				} else {
					tmp.setStartMM(smCron[1].trim());
					tmp.setEndMM("0");
					tmp.setInterval("0");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			sRtn = "";
		}
		return tmp;
	
	}
	
	/**
	 * QuartzCron 排程說明
	 */
	private String getPresentationQuartzCron(String sCron) {
		StringBuffer sRtn = new StringBuffer();
		StringBuffer weekly = new StringBuffer();
		String[] smCron = sCron.split(" ");
		String interval = "";
		try {
			if (smCron[5].equals("*") || smCron[5].equals("?")) {
				// sRtn.append("每年");
			} else {
				weekly.append("週");
				String[] value;
				if (smCron[5].contains(",")) {
					weekly.append(smCron[5]);
				} else {
					value = smCron[5].split("-");
					for (int i = 0; i < value.length; i++) {
						if (i > 0)
							sRtn.append("-");
						weekly.append(getWeekly(value[i]));
					}
				}
			}

			if (smCron[4].equals("*")) {
				sRtn.append("每月");
			} else if (!smCron[4].equals("*") && !smCron[4].equals("?")) {
				sRtn.append(smCron[4]).append("月");
			}
			if (smCron[3].equals("*")) {
				sRtn.append("每日");
			} else if (smCron[3].equals("?")) {
				sRtn.append(weekly);
			} else {
				sRtn.append(smCron[3]).append("號");
			}
			if (smCron[2].equals("*")) {
				sRtn.append("每時");
			} else {
				sRtn.append(smCron[2] + "時");
			}
			if (smCron[1].equals("*")) {
				sRtn.append("每分");
			} else {
				if (smCron[1].contains("/")) {
					sRtn.append(smCron[1].substring(0, smCron[1].indexOf("/")) + "分");
					interval = " , 間隔" + smCron[1].substring((smCron[1].indexOf("/")) + 1) + "分鐘";
				} else
					sRtn.append(smCron[1] + "分");
			}
			if (smCron[0].equals("*")) {
				sRtn.append("每秒");
			} else {
				sRtn.append(smCron[0] + "秒");
			}
			sRtn.append(interval);
		} catch (Exception ex) {
			ex.printStackTrace();
			sRtn = new StringBuffer(sCron);
		}
		return sRtn.toString();
	}
	
	private String getWeekly(String s) {
		String[] v = new String[] { "日", "一", "二", "三", "四", "五", "六" };
		String[] w0 = new String[] { "SUM", "MON", "TUE", "WED", "THU", "FRI", "SAT" };
		String[] w1 = new String[] { "1", "2", "3", "4", "5", "6", "7" };
		String sRtn = s;
		for (int i = 0; i < w0.length; i++) {
			if (s.equals(w1[i]) || s.equals(w0[i])) {
				sRtn = v[i];
				break;
			}
		}
		return sRtn;
	}

	@Override
	public Page<SysScheduler> listAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

//	public String formatDate(String date) {
//		String[] tmp = date.split(" ");
//		StringBuffer sRtn = new StringBuffer();
//		sRtn.append(tmp[0].replaceAll("-", "/"));
//		sRtn.append(" ");
//		sRtn.append(tmp[1].subSequence(0, tmp[1].indexOf(".")));
//		return sRtn.toString();
//	}

}
