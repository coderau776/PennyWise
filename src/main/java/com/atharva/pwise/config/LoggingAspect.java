package com.atharva.pwise.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* * com.atharva.pwise.*.*(..))")
    private void methodsFromAllPackages() {
    }
    @Before(value = "methodsFromAllPackages()")
    public void logBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String methodName = joinPoint.getSignature().getName();
        LOGGER.info("method {}() called with arguments - {}", methodName, Arrays.toString(args));
    }
    @AfterReturning(value = "methodsFromAllPackages()", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        LOGGER.info("method {}() returned - {}", methodName, result);
    }
}