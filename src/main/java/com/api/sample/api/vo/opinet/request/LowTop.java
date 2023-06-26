package com.api.sample.api.vo.opinet.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LowTop {

    private String code;
    private String out;
    @ApiModelProperty(position = 1, required = true, example = "B034:고급휘발유, B027:보통휘발유, D047:자동차경유, C004:실내등유, K015:자동차부탄 (미입력시 모든 제품 조회)")
    private String prodcd;
    @ApiModelProperty(position = 2, example = "시도코드(2자리): 해당시도 기준, 시군코드(4자리) : 해당시군 기준")
    private String area;
    @ApiModelProperty(position = 3, example = "최저가순 결과 건수 (1 ~ 20 사이 숫자 입력, 미입력시 기본값 10건)")
    private int cnt;



}
