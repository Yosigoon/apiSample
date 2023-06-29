package com.api.sample.api.service;

import com.api.sample.api.vo.opinet.request.*;
import com.api.sample.api.vo.opinet.response.Response;
import com.api.sample.util.OpinetUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class OpinetService {

    @Value("${opinet.key}")
    private String code;
    private final OpinetUtils opinetUtils;
    private final ObjectMapper objectMapper;

    /* 공통화 및 최적화 function */
    public Map<String, String> convertToMap(Object object) {
        return opinetUtils.convertToMap(object); //프라이빗으로 추출하여 중복을 제거
    }

    public StringBuilder sendGetRequest(String url, Map<String, String> paramMap) {
        return opinetUtils.sendGetRequest(url, paramMap); //프라이빗으로 추출하여 중복을 제거
    }

    public Response getResponse(StringBuilder stringBuilder) throws JsonProcessingException {
        String xml = String.valueOf(stringBuilder);
        JSONObject jsonObject = XML.toJSONObject(xml);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        return objectMapper.readValue(jsonObject.toString(), Response.class);
    }


    /* API Service */

    public Response getAreaCode(AreaCode areaCode) throws JsonProcessingException {
        log.info("## API: getAreaCode, Object: {}", areaCode);
        areaCode.setCode(code);
        Map<String, String> paramMap = convertToMap(areaCode);
        StringBuilder stringBuilder = sendGetRequest("/areaCode.do", paramMap);

        return getResponse(stringBuilder);
    }

    public Response getSigunPrice(SigunPrice sigunPrice) throws JsonProcessingException {
        log.info("## API: getSigunPrice, Object: {}", sigunPrice);
        sigunPrice.setCode(code);
        Map<String, String> paramMap = convertToMap(sigunPrice);
        StringBuilder stringBuilder = sendGetRequest("/avgSigunPrice.do", paramMap);

        return getResponse(stringBuilder);
    }


    public Response getPollAvgRecentPrice(PollAvgRecentPrice pollAvgRecentPrice) throws JsonProcessingException {
        log.info("## API: getPollAvgRecentPrice, Object: {}", pollAvgRecentPrice);
        pollAvgRecentPrice.setCode(code);
        Map<String, String> paramMap = convertToMap(pollAvgRecentPrice);
        StringBuilder stringBuilder = sendGetRequest("/pollAvgRecentPrice.do", paramMap);

        return getResponse(stringBuilder);
    }

    public Response getAreaAvgRecentPrice(AreaPollAvgRecentPrice areaPollAvgRecentPrice) throws JsonProcessingException {
        log.info("## API: getAreaAvgRecentPrice, Object: {}", areaPollAvgRecentPrice);
        areaPollAvgRecentPrice.setCode(code);
        Map<String, String> paramMap = convertToMap(areaPollAvgRecentPrice);
        StringBuilder stringBuilder = sendGetRequest("/areaAvgRecentPrice.do", paramMap);

        return getResponse(stringBuilder);
    }

    public Response getLowTop10(LowTop lowTop) throws JsonProcessingException {
        log.info("## API: getLowTop10, Object: {}", lowTop);
        lowTop.setCode(code);
        Map<String, String> paramMap = convertToMap(lowTop);
        StringBuilder stringBuilder = sendGetRequest("/lowTop10.do", paramMap);

        return getResponse(stringBuilder);
    }

    public Response getDetailById(DetailById detailById) throws JsonProcessingException {
        log.info("## API: getDetailById, Object: {}", detailById);
        detailById.setCode(code);
        Map<String, String> paramMap = convertToMap(detailById);
        StringBuilder stringBuilder = sendGetRequest("/detailById.do", paramMap);

        return getResponse(stringBuilder);
    }
}
