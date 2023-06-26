package com.api.sample.api.vo.opinet.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DetailById {
    private String code;
    private String out;
    @ApiModelProperty(position = 1, required = true, example = "주유소 ID (A0009180)")
    private String id;

}
