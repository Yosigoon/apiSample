package com.api.sample.api.vo.sweettracker.fms;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class TraceStateParamVO {
    @ApiModelProperty(position = 1, example = "20220101", required = true)
    @NotBlank
    private String startDt;     //조회시작일자

    @ApiModelProperty(position = 2, example = "20220301", required = true)
    @NotBlank
    private String endDt;       //조회종료일자

    @ApiModelProperty(position = 3, example = "DT01", required = true)
    private String whCd;        //센터코드

    @ApiModelProperty(position = 4, example = "00001")
    private String cstCd;       //고객사코드

    @ApiModelProperty(position = 5, example = "552861755050")
    private String invoiceNo;   //송장번호

    @ApiModelProperty(position = 6, example = "HANJIN")
    private String parcelCd;    //택배사코드
}
