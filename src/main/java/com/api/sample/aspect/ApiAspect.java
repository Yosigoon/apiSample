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
     *   @RequestMapping 설정된 메소드 또는 클래스 설정
     *   어노테이션이 설정된 특정 클래스/메소드에만 AspectJ가 적용됨.
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void requestMapping(){ }

    /**
     *   @GetMapping 설정된 메소드 또는 클래스 설정
     *   어노테이션이 설정된 특정 클래스/메소드에만 AspectJ가 적용됨.
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void getMapping(){ }

    /**
     *   @PostMapping 설정된 메소드 또는 클래스 설정
     *   어노테이션이 설정된 특정 클래스/메소드에만 AspectJ가 적용됨.
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postMapping(){ }

    /**
     * @param joinPoint
     */
    @Before("requestMapping()")
    public void before(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        LogThreadLocal.myLogThreadLocal.set(request.getHeader("traceUUID"));
        log.info("===================== AspectJ: Before Logging Start=====================");
        log.info("===================== ThreadLocal: "+ LogThreadLocal.myLogThreadLocal.get() +"=====================");
        log.info("===================== AspectJ: Before Logging End=====================");
    }

    /**
     * @param joinPoint
     * @param result
     */
    @AfterReturning(pointcut = "requestMapping()", returning = "result")
    public void AfterReturning(JoinPoint joinPoint, Object result) {
        LogThreadLocal.myLogThreadLocal.remove();
        log.info("===================== AspectJ: AfterReturning Logging Start=====================");
        log.info("===================== AspectJ: AfterReturning Logging END=====================");
    }

    /**
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("requestMapping()")
    public Object Around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("===================== AspectJ: Around Logging Start=====================");
        Object result = joinPoint.proceed();
        try {
            log.info("===================== AspectJ: Around Logging END=====================");
            log.info(result.toString());
        }catch (Exception e) {
            log.error("===================== AspectJ Around Exception=====================");
            log.error(e.toString());
        }
        return result;
    }

}
