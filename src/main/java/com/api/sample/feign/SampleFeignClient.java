package com.api.sample.feign;

import com.api.sample.api.vo.SampleVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="default", url="${api.url}")
public interface SampleFeignClient {

    @PostMapping("/apiTestP")
    SampleVO test(@RequestHeader("traceUUID") String headers, SampleVO sampleVO);
}
