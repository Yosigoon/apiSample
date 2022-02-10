package com.api.sample.api.controller;

import com.api.sample.api.service.ApiSampleService;
import com.api.sample.apiException.ApiException;
import com.api.sample.apiException.ExceptionEnum;
import com.api.sample.api.vo.SampleVO;
import com.api.sample.feign.SampleFeignClient;
import com.api.sample.threadLocal.LogThreadLocal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
public class ApiTestController {
    @Autowired
    SampleFeignClient sampleFeignClient;

    @Autowired
    ApiSampleService apiSampleService;

    @GetMapping(value = "/apiTestS")
    public String apiTest () {
        throw new ApiException(ExceptionEnum.SECURITY_01);
    }

    @GetMapping(value = "/apiTestR")
    public String apiTestR () {
        throw new ApiException(ExceptionEnum.RUNTIME_EXCEPTION);
    }

    @GetMapping(value = "/apiTestA")
    public String apiTestA () {
        throw new ApiException(ExceptionEnum.ACCESS_DENIED_EXCEPTION);
    }

    @GetMapping(value = "/apiTestI")
    public String apiTestI () {
        throw new ApiException(ExceptionEnum.INTERNAL_SERVER_ERROR);
    }

    //@PostMapping(value = "/apiTestP")
    @RequestMapping(value = "/apiTestP", method=RequestMethod.POST)
    public SampleVO apiTestP (@Valid SampleVO sampleVO) {
        sampleVO.setSampleId("RETURN TESTID");
        sampleVO.setSampleNm("RETURN TESTNM");

        log.info("=====================ThreadLocal TEST  : "+ LogThreadLocal.myLogThreadLocal.get() +"=====================");

        return sampleVO;
    }

    @GetMapping(value = "/apiTestE")
    public String apiTestE () {
        String ptr = null;

        if (ptr.equals("abc")){
            log.info("NullPointerException");
        }

        return "";
    }

    //@GetMapping(value = "/apiTestF")
    @RequestMapping(value = "/apiTestF", method=RequestMethod.GET)
    public SampleVO apiTestF () {

        SampleVO sampleVO = SampleVO.builder()
                .sampleId("yosi")
                .sampleNm("이형원")
                .build();
        return sampleFeignClient.test(LogThreadLocal.myLogThreadLocal.get(), sampleVO);
    }

    @GetMapping("/getDate")
    public String getDate (){
        int no = 1;
        String now = apiSampleService.getDate(no);
        log.info("now: {}", now);
        return now;
    }
}






