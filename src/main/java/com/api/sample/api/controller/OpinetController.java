package com.api.sample.api.controller;

import com.api.sample.api.service.OpinetService;
import com.api.sample.api.vo.opinet.SigunPrice;
import com.api.sample.api.vo.opinet.SigunResponseVO;
import com.api.sample.api.vo.sweettracker.fms.TraceListVO;
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


    @ApiOperation(value = "시군구별 주유소 평균 가격", response = TraceListVO.class, responseContainer = "List", notes = "invoiceNo: 송장번호, parcelCd: 택배사코드")
    @GetMapping("/avgSigunPrice")
    public StringBuilder getSigunPrice(@Valid SigunPrice sigunPrice){
        return opinetService.getSigunPrice(sigunPrice);
    }

}






