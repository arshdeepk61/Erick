package com.ecommerce.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    /**
     * Pointcut that matches all methods in the com.ecommerce.aop package.
     */
    @Pointcut("execution(* com.ecommerce.aop.*.*(..))")
    public void aopPackagePointcut() {}

    /**
     * Advice that runs BEFORE the method execution.
     */
    @Before("aopPackagePointcut()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("[AOP] Before method: {} with arguments: {}", 
                joinPoint.getSignature().toShortString(), 
                Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * Advice that runs AFTER the method execution (regardless of success or failure).
     */
    @After("aopPackagePointcut()")
    public void logAfter(JoinPoint joinPoint) {
        log.info("[AOP] After method: {}", joinPoint.getSignature().toShortString());
    }

    /**
     * Advice that runs AFTER the method returns successfully.
     */
    @AfterReturning(pointcut = "aopPackagePointcut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("[AOP] After returning from: {}. Result: {}", 
                joinPoint.getSignature().toShortString(), result);
    }

    /**
     * Advice that runs AFTER the method throws an exception.
     */
    @AfterThrowing(pointcut = "aopPackagePointcut()", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        log.error("[AOP] Exception in: {}. Message: {}", 
                joinPoint.getSignature().toShortString(), error.getMessage());
    }

    /**
     * Advice that runs AROUND the method execution.
     * This is the most powerful advice, allowing you to control whether the method executes,
     * modify the arguments, or modify the return value.
     */
    @Around("@annotation(com.ecommerce.aop.TrackTime)")
    public Object trackTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        
        // Execute the actual method
        Object result = joinPoint.proceed();
        
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        
        log.info("[AOP] Method {} executed in {} ms", 
                joinPoint.getSignature().toShortString(), executionTime);
        
        return result;
    }
}
