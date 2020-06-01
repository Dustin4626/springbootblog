package com.dustin.springbootblog.web.dao;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dustin.springbootblog.model.SysScheduler;

@Repository
public interface JobRepository extends JpaRepository<SysScheduler, BigDecimal> {

// option method
	static String LIST_QUERY = " FROM SYS_SCHEDULER A "
			+ "LEFT JOIN SYS_LOG_SCHEDULER_FIRED B ON B.JOB_ID = A.JOB_ID "
			+ " ,( SELECT  JOB_ID ,max(ID) ID FROM  SYS_LOG_SCHEDULER_FIRED GROUP BY JOB_ID) C "
			+ "WHERE B.ID IS NULL OR B.ID = C.id AND B.JOB_ID = C.JOB_ID "
			+ "GROUP BY  A.JOB_ID, A.JOB_NAME, A.RUN_AP, "
			+ "A.QUARTZ_CRON, A.REMARK, B.FIRED_DATE, "
			+ "B.FIRED_DESC,A.VERSION  ORDER BY A.VERSION ";
	
	@Query(value="SELECT A.JOB_ID jobId, A.JOB_NAME jobName, A.RUN_AP runAp, A.QUARTZ_CRON quartzCron, A.REMARK remark"
				+ ", B.FIRED_DATE firedDate, B.FIRED_DESC firedDesc "+ LIST_QUERY
				
				//no add countQuery will cause "Unknown column 'A' in 'field list'"
//			, countQuery = "select count(A.JOB_ID) " + LIST_QUERY
			, nativeQuery = true)
	Page<Map<String, String>> list(Pageable pageable);
	
	public SysScheduler findByJobId(String jobId);
	
}
