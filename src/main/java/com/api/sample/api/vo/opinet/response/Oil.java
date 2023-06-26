package com.api.sample.api.vo.opinet.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Oil {
    @JsonProperty("PRODCD")
    private String PRODCD;
    @JsonProperty("PRICE")
    private double PRICE;
    @JsonProperty("DIFF")
    private int DIFF;
    @JsonProperty("SIGUNCD")
    private String SIGUNCD;
    @JsonProperty("SIGUNNM")
    private String SIGUNNM;
    @JsonProperty("AREA_CD")
    private String AREA_CD;
    @JsonProperty("AREA_NM")
    private String AREA_NM;
}
