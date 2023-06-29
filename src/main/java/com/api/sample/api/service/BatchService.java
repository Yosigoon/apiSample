package com.api.sample.api.service;

import com.api.sample.api.vo.opinet.request.DetailById;
import com.api.sample.api.vo.opinet.response.OilPrice;
import com.api.sample.api.vo.opinet.response.Response;
import com.api.sample.enums.OpinetCode;
import com.api.sample.util.OpinetUtils;
import com.api.sample.util.SlackUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class BatchService {

    private static final List<String> idList = Arrays.asList("A0009180", "A0009218", "A0009211");

    @Value("${opinet.key}")
    private String code;

    private final OpinetUtils opinetUtils;

    private final OpinetService opinetService;

    private final SlackUtil slackUtil;

    @Scheduled(cron = "0 30 9,12,16,19 * * *")
    public void myOilInfo() throws JsonProcessingException {
        DetailById detailById = new DetailById();
        detailById.setCode(code);
        for (String id  : idList){
            detailById.setId(id);
            Map<String, String> paramMap = opinetService.convertToMap(detailById);
            StringBuilder stringBuilder = opinetService.sendGetRequest("/detailById.do", paramMap);

            Response response = opinetService.getResponse(stringBuilder);
            Object object = ((LinkedHashMap) response.getResult().getOil()).get("OIL_PRICE");
            ObjectMapper objectMapper = new ObjectMapper();
            List<OilPrice> oilPriceList = objectMapper.convertValue(object, new TypeReference<List<OilPrice>>() {});

            OilPrice oil = new OilPrice();
            for (OilPrice oilPrice : oilPriceList){
                if (oilPrice.getPRODCD().equals(OpinetCode.보통휘발유.getCode())) {
                    oil.setPRODCD(oilPrice.getPRODCD());
                    oil.setPRICE(oilPrice.getPRICE());
                    oil.setTRADE_DT(oilPrice.getTRADE_DT());
                    oil.setTRADE_TM(oilPrice.getTRADE_TM());
                }
            }

            try {
                StringBuilder msg = new StringBuilder();
                String lineSeparator = System.getProperty("line.separator");
                msg.append("------------------------------------------").append(lineSeparator)
                        .append("[" + opinetUtils.convertPollDivCo((String) ((LinkedHashMap) response.getResult().getOil()).get("POLL_DIV_CO")) + "] "
                                +((LinkedHashMap) response.getResult().getOil()).get("OS_NM")).append(lineSeparator)
                        .append("주소: " + ((LinkedHashMap) response.getResult().getOil()).get("VAN_ADR")).append(lineSeparator)
                        .append("도로명주소: " + ((LinkedHashMap) response.getResult().getOil()).get("NEW_ADR")).append(lineSeparator)
                        .append("휘발유가격: " + oil.getPRICE()).append(lineSeparator)
                        .append("기준일시: " + opinetUtils.convertDateTime(oil.getTRADE_DT(), oil.getTRADE_TM())).append(lineSeparator)
                        .append("------------------------------------------");

                slackUtil.send(msg.toString());
            }catch (Exception e){
                log.info("## slack send exception {}", e.getMessage());
            }
        }
    }

}
