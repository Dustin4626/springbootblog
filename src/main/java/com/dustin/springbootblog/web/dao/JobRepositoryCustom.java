package com.dustin.springbootblog.web.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dustin.springbootblog.model.SysScheduler;
import com.dustin.springbootblog.model.SysSchedulerFiredList;

public interface JobRepositoryCustom {
	Page<SysSchedulerFiredList> jobIndexListAll(Pageable pageable);
}
