package com.api.sample.api.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CommonSystemVO {
    @ApiModelProperty(hidden = true)
    private String regId;
    @ApiModelProperty(hidden = true)
    private String modId;
    @ApiModelProperty(hidden = true)
    private String regTime;
    @ApiModelProperty(hidden = true)
    private String modTime;
    @ApiModelProperty(hidden = true)
    private String level; //배송단계(1~6단계), -99 배송 스캔 오류
    @ApiModelProperty(hidden = true)
    private String details;
}
