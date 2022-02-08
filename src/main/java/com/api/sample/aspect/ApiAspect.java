package com.api.sample.aspect;

import com.api.sample.threadLocal.LogThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class ApiAspect {

    /**
     *   @GetMapping 설정된 메소드 또는 클래스 설정
     *   GetMapping 노테이션이 설정된 특정 클래스/메소드에만 AspectJ가 적용됨.
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void GetMapping(){ }

    /**
     * @param joinPoint
     */
    @Before("GetMapping()")
    public void before(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        LogThreadLocal.myLogThreadLocal.set(request.getHeader("traceUUID"));
        log.info("=====================AspectJ TEST  : Before Logging Start=====================");
        log.info("=====================ThreadLocal TEST  : "+ LogThreadLocal.myLogThreadLocal.get() +"=====================");
        log.info("=====================AspectJ TEST  : Before Logging End=====================");
    }

    /**
     * @param joinPoint
     * @param result
     */
    @AfterReturning(pointcut = "GetMapping()", returning = "result")
    public void AfterReturning(JoinPoint joinPoint, Object result) {
        LogThreadLocal.myLogThreadLocal.remove();
        log.info("=====================AspectJ TEST  : AfterReturning Logging Start=====================");
        log.info("=====================AspectJ TEST  : AfterReturning Logging END=====================");
    }

    /**
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("GetMapping()")
    public Object Around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("=====================AspectJ TEST  : Around Logging Start=====================");
        Object result = joinPoint.proceed();
        try {
            log.info("=====================AspectJ TEST  : Around Logging END=====================");
            log.info(result.toString());
        }catch (Exception e) {
            log.error("=====================AspectJ Around Exception=====================");
            log.error(e.toString());
        }
        return result;
    }

}
