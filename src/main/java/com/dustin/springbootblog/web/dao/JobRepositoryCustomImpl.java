package com.dustin.springbootblog.web.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.query.spi.NativeQueryImplementor;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.dustin.springbootblog.model.SysScheduler;
import com.dustin.springbootblog.model.SysSchedulerFiredList;

public class JobRepositoryCustomImpl extends GenericDao<SysScheduler, BigDecimal> implements JobRepositoryCustom {

//	@PersistenceContext
//	private EntityManager entityManager;

	public Page<SysSchedulerFiredList> jobIndexListAll1(Pageable pageable) {
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
		
		
		return null;
	}

	@Override
	public Page<SysSchedulerFiredList> jobIndexListAll(Pageable pageable) {
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
		
		StringBuffer sb1 = new StringBuffer();
		sb1.append(" SELECT count(*) ");
		sb1.append(" FROM SYS_SCHEDULER A ");
		sb1.append(" LEFT JOIN SYS_LOG_SCHEDULER_FIRED B ON B.JOB_ID = A.JOB_ID ");
		sb1.append(" ,( SELECT  JOB_ID ,max(ID) ID FROM  SYS_LOG_SCHEDULER_FIRED GROUP BY JOB_ID) C ");
		sb1.append(" WHERE B.ID IS NULL OR B.ID = C.id AND B.JOB_ID = C.JOB_ID ");
		Query countQuery = entityManager.createNativeQuery(sb1.toString());
		Query dataQuery = entityManager.createNativeQuery(sb.toString());
		NativeQueryImplementor nativeQueryImplementor = 
				dataQuery.unwrap(NativeQueryImpl.class).
				setResultTransformer(Transformers.aliasToBean(SysSchedulerFiredList.class));
		
//		List<SysSchedulerFiredList> resultList = nativeQueryImplementor.getResultList();
//		List<Map<String, String>> resultList = nativeQueryImplementor.getResultList();
		Page<SysSchedulerFiredList> resultList = (pageable == null ? new PageImpl(dataQuery.getResultList()) : this.readPage(dataQuery, countQuery, pageable));
		return resultList;
	}

	private Page<SysSchedulerFiredList> readPage(Query dataQuery, Query countQuery, Pageable pageable) {
		int offset = (int) pageable.getOffset();
		System.out.println("setFirstResult:pageable.getOffset()="+offset);
		System.out.println("setMaxResults:pageable.getPageSize()="+pageable.getPageSize());
		
		dataQuery.setFirstResult((int) offset);
		dataQuery.setMaxResults(pageable.getPageSize());
		BigInteger singleResult = (BigInteger)countQuery.getSingleResult();
		Long totalSize = singleResult.longValue();
		List<SysSchedulerFiredList> content = totalSize > (long) offset ? dataQuery.getResultList() : Collections.emptyList();
		return new PageImpl(content, pageable, totalSize);
	}
}
