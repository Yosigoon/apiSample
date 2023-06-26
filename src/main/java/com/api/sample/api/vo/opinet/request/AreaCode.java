package com.api.sample.api.vo.opinet.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AreaCode {
    private String code;
    private String out;
    @ApiModelProperty(position = 1, example = "시군구 코드 (미입력시 전체)")
    private String area;

}
