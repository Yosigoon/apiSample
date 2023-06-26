package com.api.sample.api.vo.opinet.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OilPrice {
    @JsonProperty("PRODCD")
    private String PRODCD;      //제품구분
    @JsonProperty("PRICE")
    private double PRICE;       //판매가격
    @JsonProperty("TRADE_DT")
    private String TRADE_DT;    //기준일자
    @JsonProperty("TRADE_TM")
    private String TRADE_TM;    //기준시간

}
