package com.dustin.springbootblog.aspect;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LogAspect {
	private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

	@Pointcut("execution(* com.dustin.springbootblog.web.*.*.*(..))")
	public void log() {
	}

	@Before("log()")
	public void doBefore(JoinPoint joinPoint) {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();

		RequestLog requestLog = new RequestLog(
				request.getRequestURL().toString(),
				request.getRemoteAddr(),
				joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName(),
				joinPoint.getArgs());
		log.info("Request : {}", requestLog);
	}

	@After("log()")
	public void doAfter() {
//		log.info("--doAfter--");
	}

	@AfterReturning(returning = "result", pointcut = "log()")
	public void afterReturning(Object result) {
		log.info("Response : [{}]", result);
	}

	private class RequestLog {
		private String url;
		private String ip;
		private String classMethod;
		private Object[] args;

		public RequestLog(String url, String ip, String classMethod, Object[] args) {
			super();
			this.url = url;
			this.ip = ip;
			this.classMethod = classMethod;
			this.args = args;
		}

		@Override
		public String toString() {
			return "[url=" + url + ", ip=" + ip + ", classMethod=" + classMethod + ", args=" + Arrays.toString(args) + "]";
		}
	}
}
