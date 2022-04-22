package com.api.sample.api.vo.sweettracker;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AddInvoiceResponseVO {
    @ApiModelProperty(position = 1, example = "true or false")
    private boolean success;     //성공여부(true, false)
    @ApiModelProperty(position = 2, example = "512580474420")
    private String num;         //운송장 번호
    @ApiModelProperty(position = 3, example = "05_512580474420")
    private String fid;         //식별 값
    @ApiModelProperty(position = 4, example = "null or 02")
    private String e_code;      //에러메시지 코드
    @ApiModelProperty(position = 5, example = "null or 송장번호 규칙에 어긋납니다.")
    private String e_message;   //에러메시지
}
