package com.donipop.livestreaming.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Aop {

    @Around("execution(* com.donipop.livestreaming..*(..))")
    public Object time(ProceedingJoinPoint p) throws Throwable {
        long start = System.currentTimeMillis();
        Object re = p.proceed();
        System.out.println(p.getSignature() +" : " + (System.currentTimeMillis() - start) + "ms");
        return re;
    }
}
