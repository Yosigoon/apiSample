package com.api.sample.api.vo.sweettracker;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CompanyListVO {
    @JsonProperty("Name")
    @ApiModelProperty(position = 1, example = "한진택배")
    private String Name;

    @JsonProperty("Code")
    @ApiModelProperty(position = 2, example = "05")
    private String Code;
}
