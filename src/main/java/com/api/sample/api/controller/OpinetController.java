package com.api.sample.api.controller;

import com.api.sample.api.service.OpinetService;
import com.api.sample.api.vo.opinet.request.AreaCode;
import com.api.sample.api.vo.opinet.request.SigunPrice;
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

    @ApiOperation(value = "지역코드", response = Response.class, responseContainer = "List", notes = "invoiceNo: 송장번호, parcelCd: 택배사코드")
    @GetMapping("/areaCode")
    public Response getAreaCode(@Valid AreaCode areaCode) throws JsonProcessingException {
        return opinetService.getAreaCode(areaCode);
    }


    @ApiOperation(value = "시군구별 주유소 평균 가격", response = Response.class, responseContainer = "List", notes = "invoiceNo: 송장번호, parcelCd: 택배사코드")
    @GetMapping("/avgSigunPrice")
    public Response getSigunPrice(@Valid SigunPrice sigunPrice) throws JsonProcessingException {
        return opinetService.getSigunPrice(sigunPrice);
    }

}






