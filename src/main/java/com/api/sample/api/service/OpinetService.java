package com.api.sample.api.service;

import com.api.sample.api.vo.opinet.SigunPrice;
import com.api.sample.util.OpinetUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class OpinetService {

    @Value("${opinet.key}")
    private String code;

    @Value("${opinet.out}")
    private String out;

    private final OpinetUtil opinetUtil;

    public StringBuilder getSigunPrice(SigunPrice sigunPrice) {
        sigunPrice.setCode(code);
        //sigunPrice.setOut(out);
        Map<String, String> paramMap = opinetUtil.convertToMap(sigunPrice);
        StringBuilder stringBuilder = opinetUtil.sendGetRequest("/avgSigunPrice.do", paramMap);
        return stringBuilder;
    }
}
