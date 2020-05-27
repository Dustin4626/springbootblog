package com.dustin.springbootblog.web.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dustin.springbootblog.model.SysSchedulerFiredList;

public interface JobRepositoryCustom {
	Page<SysSchedulerFiredList> jobIndexListAll(Pageable pageable);
}
