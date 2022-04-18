package com.revature.project2.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @After(value = "execution(* com.revature.project2.*.*.*(..))")
    public void afterAdvice(JoinPoint joinPoint) {
        logger.info("User is making a request...");
        logger.info("Method: " + joinPoint.getSignature().getName());
        logger.info("Path: " + joinPoint.getSignature().getDeclaringTypeName());
        logger.info("Target: " + joinPoint.getTarget().getClass().getSimpleName());
        logger.info("User request is now completed.");
    }
}
