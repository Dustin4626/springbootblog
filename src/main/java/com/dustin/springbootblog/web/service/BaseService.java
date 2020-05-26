package com.dustin.springbootblog.web.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseService<T, ID extends Serializable>{

	@Autowired
	public GenericService<T,ID> genericService;
	
	public abstract Class defaultClass();

//	/**
//	 * 取得Service
//	 * 
//	 * @param beanName
//	 * @param clazz
//	 * @return
//	 */
//	protected GenericService service(String beanName, Class clazz) {
//		return genericService.entity(clazz);
//	}

	/**
	 * 取得Service
	 * 
	 * @param clazz
	 * @return
	 */
	protected GenericService defaultSev(Class clazz) {
		return genericService.entity(clazz);
	}

	/**
	 * 取得預設Service
	 * 
	 * @return
	 */
	protected GenericService defaultSev() {
		return defaultSev(defaultClass());
	}

//	/**
//	 * 取得Service
//	 * 
//	 * @param beanName
//	 * @param clazz
//	 * @return
//	 */
//	protected GenericServiceTurnkey serviceTurnkey(String beanName, Class clazz) {
//		return ((GenericServiceTurnkey) ContextUtil.getContext().getBean(beanName)).entity(clazz);
//	}
//
//	/**
//	 * 取得Service
//	 * 
//	 * @param clazz
//	 * @return
//	 */
//	protected GenericServiceTurnkey defaultSevTurnkeyV32(Class clazz) {
//		return serviceTurnkey("genericService6", clazz);
//	}
//
//	/**
//	 * 取得Service
//	 * 
//	 * @param beanName
//	 * @param clazz
//	 * @return
//	 */
//	protected GenericServiceTurnkey defaultSevTurnkeyV32() {
//		return defaultSevTurnkeyV32(defaultClass());
//	}
//
//	/**
//	 * 取得Service
//	 * 
//	 * @param clazz
//	 * @return
//	 */
//	protected GenericServiceTurnkey defaultSevTurnkey(Class clazz) {
//		return serviceTurnkey("genericService2", clazz);
//	}
//
//	/**
//	 * 取得預設Service
//	 * 
//	 * @return
//	 */
//	protected GenericServiceTurnkey defaultSevTurnkey() {
//		return defaultSevTurnkey(defaultClass());
//	}
//
//	/**
//	 * 取得ServiceB2B
//	 * 
//	 * @param clazz
//	 * @return
//	 */
//	protected GenericServiceB2b serviceB2B(Class clazz) {
//		return ((GenericServiceB2b) ContextUtil.getContext().getBean("genericService3")).entity(clazz);
//	}
//
//	/**
//	 * 取得ServiceTice
//	 * 
//	 * @param clazz
//	 * @return
//	 */
//	protected GenericService serviceTice(Class clazz) {
//		return ((GenericService) ContextUtil.getContext().getBean("genericService4")).entity(clazz);
//	}
//
//	/**
//	 * 取得ServicePos add 2018/07/30
//	 * 
//	 * @param clazz
//	 * @return
//	 */
//	protected GenericService servicePos(Class clazz) {
//		return ((GenericService) ContextUtil.getContext().getBean("genericService5")).entity(clazz);
//	}
}
