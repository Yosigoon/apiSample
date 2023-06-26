package com.api.sample.api.vo.opinet.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PollAvgRecentPrice {

    private String code;
    private String out;
    @ApiModelProperty(position = 1, example = "B034:고급휘발유, B027:보통휘발유, D047:자동차경유, C004:실내등유, K015:자동차부탄 (미입력시 모든 제품 조회)")
    private String prodcd;
    @ApiModelProperty(position = 2, example = "SKE:SK에너지, GSC:GS칼텍스, HDO:현대오일뱅크, SOL:S-OIL, RTO:자영알뜰, RTX:고속 도로알뜰, NHO:농협알뜰, ETC:자가상표, (미입력시 모든 상표조회)")
    private String pollcd;

}
