package com.api.sample.apiTest;

import com.api.sample.apiException.ApiException;
import com.api.sample.apiException.ExceptionEnum;
import com.api.sample.apiTest.vo.SampleVO;
import com.api.sample.feign.SampleFeignClient;
import com.api.sample.threadLocal.LogThreadLocal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ApiTestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SampleFeignClient sampleFeignClient;

    @RequestMapping(value = "/apiTestS", method= RequestMethod.GET)
    public String apiTest () {
        throw new ApiException(ExceptionEnum.SECURITY_01);
    }

    @RequestMapping(value = "/apiTestR", method=RequestMethod.GET)
    public String apiTestR () {
        throw new ApiException(ExceptionEnum.RUNTIME_EXCEPTION);
    }

    @RequestMapping(value = "/apiTestA", method=RequestMethod.GET)
    public String apiTestA () {
        throw new ApiException(ExceptionEnum.ACCESS_DENIED_EXCEPTION);
    }

    @RequestMapping(value = "/apiTestI", method=RequestMethod.GET)
    public String apiTestI () {
        throw new ApiException(ExceptionEnum.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/apiTestP", method=RequestMethod.POST)
    public SampleVO apiTestP (@RequestBody @Valid SampleVO sampleVO) {
        sampleVO.setSampleId("RETURN TESTID");
        sampleVO.setSampleNm("RETURN TESTNM");

        logger.info("=====================ThreadLocal TEST  : "+ LogThreadLocal.myLogThreadLocal.get() +"=====================");

        return sampleVO;
    }

    @RequestMapping(value = "/apiTestE", method=RequestMethod.GET)
    public String apiTestE () {
        String ptr = null;

        if (ptr.equals("abc")){
            logger.info("NullPointerException");
        }

        return "";
    }

    @RequestMapping(value = "/apiTestF", method=RequestMethod.GET)
    public SampleVO apiTestF () {

        SampleVO sampleVO = SampleVO.builder()
                .sampleId("yosi")
                .sampleNm("이형원")
                .build();
        return sampleFeignClient.test(LogThreadLocal.myLogThreadLocal.get(), sampleVO);
    }


}






