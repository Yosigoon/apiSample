package com.api.sample.aspect;

import com.api.sample.threadLocal.LogThreadLocal;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class ApiAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
        logger.info("=====================AspectJ TEST  : Before Logging Start=====================");
        logger.info("=====================ThreadLocal TEST  : "+ LogThreadLocal.myLogThreadLocal.get() +"=====================");
        logger.info("=====================AspectJ TEST  : Before Logging End=====================");
    }

    /**
     * @param joinPoint
     * @param result
     */
    @AfterReturning(pointcut = "GetMapping()", returning = "result")
    public void AfterReturning(JoinPoint joinPoint, Object result) {
        LogThreadLocal.myLogThreadLocal.remove();
        logger.info("=====================AspectJ TEST  : AfterReturning Logging Start=====================");
        logger.info("=====================AspectJ TEST  : AfterReturning Logging END=====================");
    }

    /**
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("GetMapping()")
    public Object Around(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("=====================AspectJ TEST  : Around Logging Start=====================");
        Object result = joinPoint.proceed();
        try {
            logger.info("=====================AspectJ TEST  : Around Logging END=====================");
            logger.info(result.toString());
        }catch (Exception e) {
            logger.error("=====================AspectJ Around Exception=====================");
            logger.error(e.toString());
        }
        return result;
    }

}
