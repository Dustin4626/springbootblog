package com.dustin.springbootblog;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
	
	private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

	@Pointcut("execution(* com.dustin.springbootblog.web.*.*(..))")
	public void log() {}
	
	
}
