package com.api.sample.api.vo.sweettracker.fms;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class AddInvoiceListRequestParamVo {
    @ApiModelProperty(position = 1, example = "20220504", required = true)
    @NotBlank
    private String packDt;      //패킹완료일자

    @ApiModelProperty(position = 2, example = "YI01", required = true)
    @NotBlank
    private String whCd;        //센터코드

    @ApiModelProperty(position = 3, example = "HANJIN", required = true)
    @NotBlank
    private String parcelCd;    //택배사코드
}
