package com.api.sample.api.vo.delivery;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class TraceCommonStateVO {
    @ApiModelProperty(position = 1, example = "552861755050")
    private String invoiceNo;

    @ApiModelProperty(position = 2, example = "TEST")
    private String whCd;

    @ApiModelProperty(position = 3, example = "00001")
    private String cstCd;

    @ApiModelProperty(position = 4, example = "CJ")
    private String parcelCd;

    @ApiModelProperty(position = 5, example = "1")
    private int level;

    @ApiModelProperty(position = 6, example = "배송준비")
    private String details;
}
