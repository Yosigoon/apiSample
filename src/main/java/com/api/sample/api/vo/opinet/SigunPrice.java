package com.api.sample.api.vo.opinet;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SigunPrice {
    private String code;
    private String out;
    @ApiModelProperty(position = 1, example = "시도코드 2자리", required = true)
    private String sido;

    @ApiModelProperty(position = 2, example = "시군코드 4자리 (미입력시 전체 시군 조회 )")
    private String sigun;

    @ApiModelProperty(position = 3, example = "B034:고급휘발유, B027:보통휘발유, D047:자동차경유, C004:실내등유, K015:자동차부탄 (미입력시 모든 제품 조회)")
    private String prodcd;

}
