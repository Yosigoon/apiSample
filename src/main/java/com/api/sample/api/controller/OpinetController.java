package com.api.sample.api.controller;

import com.api.sample.api.service.OpinetService;
import com.api.sample.api.vo.opinet.request.*;
import com.api.sample.api.vo.opinet.response.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
@RequestMapping(value = "/opinet/api")
public class OpinetController {

    private final OpinetService opinetService;

    @ApiOperation(value = "지역코드 조회", response = Response.class, notes = "00: 전국/01: 서울/02: 경기/03: 강원/04: 충북/05: 충남/06: 전북/07: 전남/08: 경북/09: 경남/10: 부산/11: 제주/14: 대구/15: 인천/16: 광주/17: 대전/18: 울산/19: 세종")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "area", defaultValue = "01", required = true, dataType = "string", paramType = "query")
    })
    @GetMapping("/areaCode")
    public Response getAreaCode(@Valid AreaCode areaCode) throws JsonProcessingException {
        return opinetService.getAreaCode(areaCode);
    }


    @ApiOperation(value = "시군구별 주유소 평균 가격 조회", response = Response.class, notes = "B034:고급휘발유, B027:보통휘발유, D047:자동차경유, C004:실내등유, K015:자동차부탄 (미입력시 모든 제품 조회)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sido", defaultValue = "01", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "sigun", value = "시군코드 4자리 (미입력시 전체 시군 조회)", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "prodcd", defaultValue = "B027", dataType = "string", paramType = "query")
    })
    @GetMapping("/avgSigunPrice")
    public Response getSigunPrice(@Valid SigunPrice sigunPrice) throws JsonProcessingException {
        return opinetService.getSigunPrice(sigunPrice);
    }

    @ApiOperation(value = "최근 7일간 전국 일일 상표별 평균가격 조회", response = Response.class, notes = "prodcd => B034:고급휘발유, B027:보통휘발유, D047:자동차경유, C004:실내등유, K015:자동차부탄 (미입력시 모든 제품 조회)\npollcd => SKE:SK에너지, GSC:GS칼텍스, HDO:현대오일뱅크, SOL:S-OIL, RTO:자영알뜰, RTX:고속 도로알뜰, NHO:농협알뜰, ETC:자가상표, (미입력시 모든 상표조회)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "prodcd", dataType = "string", paramType = "query", defaultValue = "B027"),
            @ApiImplicitParam(name = "pollcd", dataType = "string", paramType = "query", defaultValue = "GSC")
    })
    @GetMapping("/pollAvgRecentPrice")
    public Response getPollAvgRecentPrice(@Valid PollAvgRecentPrice pollAvgRecentPrice) throws JsonProcessingException {
        return opinetService.getPollAvgRecentPrice(pollAvgRecentPrice);
    }

    @ApiOperation(value = "최근 7일간 전국 일일 지역별 평균가격 조회", response = Response.class, notes = "area => 시도코드(2자리): 해당시도 기준, 시군코드(4자리) : 해당시군 기준\ndate => 년월일(20230625)\npollcd => B034:고급휘발유, B027:보통휘발유, D047:자동차경유, C004:실내등유, K015:자동차부탄 (미입력시 모든 제품 조회)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "area", defaultValue = "0120", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "date", dataType = "string", paramType = "query", defaultValue = "20230625"),
            @ApiImplicitParam(name = "prodcd", dataType = "string", paramType = "query", defaultValue = "B027")
    })
    @GetMapping("/areaAvgRecentPrice")
    public Response getAreaAvgRecentPrice(@Valid AreaPollAvgRecentPrice areaPollAvgRecentPrice) throws JsonProcessingException {
        return opinetService.getAreaAvgRecentPrice(areaPollAvgRecentPrice);
    }

    @ApiOperation(value = "지역별 최저가 주유소(TOP20) 기본값 top 10 조회", response = Response.class, notes = "pollcd => B034:고급휘발유, B027:보통휘발유, D047:자동차경유, C004:실내등유, K015:자동차부탄 (미입력시 모든 제품 조회)\narea => 시도코드(2자리): 해당시도 기준, 시군코드(4자리) : 해당시군 기준\ncnt => 기본10, 최대20")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "prodcd", defaultValue = "B027", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "area", dataType = "string", paramType = "query", defaultValue = "0120"),
            @ApiImplicitParam(name = "cnt", dataType = "string", paramType = "query", defaultValue = "10")
    })
    @GetMapping("/lowTop10")
    public Response getLowTop10(@Valid LowTop lowTop) throws JsonProcessingException {
        return opinetService.getLowTop10(lowTop);
    }

    @ApiOperation(value = "주유소 상세정보(ID) 조회", response = Response.class, notes = "지역별 주유소 조회 시 id 확인")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", defaultValue = "A0009180", required = true, dataType = "string", paramType = "query")
    })
    @GetMapping("/detailById")
    public Response getDetailById(@Valid DetailById detailById) throws JsonProcessingException {
        return opinetService.getDetailById(detailById);
    }

}






