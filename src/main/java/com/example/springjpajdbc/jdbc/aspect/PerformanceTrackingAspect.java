package com.example.springjpajdbc.jdbc.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class PerformanceTrackingAspect {
	
	private Logger logger=LoggerFactory.getLogger(getClass());
	
	// Did this just to see if it works this way
	@Pointcut("execution(* com.example.springjpajdbc.jdbc.todo.*.*(..))")
	public void todoPoint() {}
	
	//1st way to do it
	@Around("todoPoint()")
	public Object findExecutionTime(ProceedingJoinPoint proceedingJointPoint) throws Throwable
	{
		
		Long startTimeMillis = System.currentTimeMillis();
		
		Object returnValue = proceedingJointPoint.proceed();
		
		Long stopTimeMillis = System.currentTimeMillis();
		
		Long executionTime =  stopTimeMillis - startTimeMillis;
		
		logger.info("Around aspect: - {} Method executed in {} milliseconds.",proceedingJointPoint ,executionTime);
		
		logger.info("Return value is: {}", returnValue);
		
		return returnValue;
	}
	
	//2nd way to do it, with annotation on method
	@Around("trackTimeAnnotation()")
	public Object findExecutionTime2(ProceedingJoinPoint proceedingJointPoint) throws Throwable
	{
		
		Long startTimeMillis = System.currentTimeMillis();
		
		Object returnValue = proceedingJointPoint.proceed();
		
		Long stopTimeMillis = System.currentTimeMillis();
		
		Long executionTime =  stopTimeMillis - startTimeMillis;
		
		logger.info("Manuel Annotation aspect: - {} Method executed in {} milliseconds.",proceedingJointPoint ,executionTime);
		
		logger.info("Return value is: {}", returnValue);
		
		return returnValue;
	}
	
	@Pointcut("@annotation(com.example.springjpajdbc.jdbc.aspect.SimpleAnnotation)")
	public void trackTimeAnnotation() {}

}
