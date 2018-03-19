package com.makerpol.advice;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.makerpol.entity.User;


@Aspect
public class logAdvice {
	public static final Logger log = LoggerFactory.getLogger(logAdvice.class);
	
	@Before("execution(* com.makerpol.controller.*.*(..))")
	public void before(JoinPoint point) {
		String methodName = point.getSignature().getName();
		log.info(new Date()+ " : " + methodName + " start.");
	}
	
	@Before("execution(* com.makerpol.controller.UserController.add*(..))")
	public void beforeAdd(JoinPoint point) {
		String methodName = point.getSignature().getName();
		log.info(new Date()+ " : " + methodName + " start.");
	}
	
	@AfterReturning("execution(* com.makerpol.controller.UserController.add*(..))")
	public void afterAdd(JoinPoint point) {
		String methodName = point.getSignature().getName();
		Object[] args = point.getArgs();
		
		User user = null;
		HttpServletRequest req = null;
		for(Object obj :args) {
			if(obj instanceof User) {
				user = (User)obj;
			}else if(obj instanceof HttpServletRequest) {
				req = (HttpServletRequest)obj;
			}
		}
		User loginUser = (User)req.getSession().getAttribute("LoginUser");
		
		log.info(new Date() + " : " + methodName);
		log.info(new Date() + " : " + user.getName());
		log.info(new Date() + " : " + loginUser.getName());
	}
}
