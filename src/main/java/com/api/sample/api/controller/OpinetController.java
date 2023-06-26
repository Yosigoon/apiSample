package com.api.sample.api.controller;

import com.api.sample.api.service.OpinetService;
import com.api.sample.api.vo.opinet.request.*;
import com.api.sample.api.vo.opinet.response.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/opinet/api")
public class OpinetController {

    private final OpinetService opinetService;

    @ApiOperation(value = "지역코드", response = Response.class)
    @GetMapping("/areaCode")
    public Response getAreaCode(@Valid AreaCode areaCode) throws JsonProcessingException {
        return opinetService.getAreaCode(areaCode);
    }


    @ApiOperation(value = "시군구별 주유소 평균 가격", response = Response.class)
    @GetMapping("/avgSigunPrice")
    public Response getSigunPrice(@Valid SigunPrice sigunPrice) throws JsonProcessingException {
        return opinetService.getSigunPrice(sigunPrice);
    }

    @ApiOperation(value = "최근 7일간 전국 일일 상표별 평균가격", response = Response.class)
    @GetMapping("/pollAvgRecentPrice")
    public Response getPollAvgRecentPrice(@Valid PollAvgRecentPrice pollAvgRecentPrice) throws JsonProcessingException {
        return opinetService.getPollAvgRecentPrice(pollAvgRecentPrice);
    }

    @ApiOperation(value = "최근 7일간 전국 일일 지역별 평균가격", response = Response.class)
    @GetMapping("/areaAvgRecentPrice")
    public Response getAreaAvgRecentPrice(@Valid AreaPollAvgRecentPrice areaPollAvgRecentPrice) throws JsonProcessingException {
        return opinetService.getAreaAvgRecentPrice(areaPollAvgRecentPrice);
    }

    @ApiOperation(value = "지역별 최저가 주유소(TOP20) 기본값 top 10", response = Response.class)
    @GetMapping("/lowTop10")
    public Response getLowTop10(@Valid LowTop lowTop) throws JsonProcessingException {
        return opinetService.getLowTop10(lowTop);
    }

    @ApiOperation(value = "주유소 상세정보(ID)", response = Response.class)
    @GetMapping("/detailById")
    public Response getDetailById(@Valid DetailById detailById) throws JsonProcessingException {
        return opinetService.getDetailById(detailById);
    }
}






