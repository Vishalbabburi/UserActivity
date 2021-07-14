package com.miracle.UserActivity.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class UserActivityAspect {
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Around("execution(* com.miracle.UserActivity.services.EmployeeActivityServices.*(..))")
    public void aroundAdvice(ProceedingJoinPoint pjp) throws Throwable{
        pjp.proceed();
    }
}
