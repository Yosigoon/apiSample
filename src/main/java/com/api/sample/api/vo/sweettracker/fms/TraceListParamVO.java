package com.api.sample.api.vo.sweettracker.fms;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class TraceListParamVO {
    @ApiModelProperty(position = 1, example = "CJ", required = true)
    @NotBlank
    private String parcelCd;

    @ApiModelProperty(position = 2, example = "346725520012", required = true)
    @NotBlank
    private String invoiceNo;

    @ApiModelProperty(hidden = true)
    private String fid;
}
