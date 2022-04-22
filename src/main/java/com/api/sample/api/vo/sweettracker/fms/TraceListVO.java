package com.api.sample.api.vo.sweettracker.fms;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TraceListVO {
    @ApiModelProperty(position = 1, example = "512588950370")
    private String invoiceNo;   //송장번호

    @ApiModelProperty(position = 2, example = "2022-04-11 13:34:00")
    private String timeTrans;   //택배사 처리시간

    @ApiModelProperty(position = 3, example = "택배위치")
    private String location;    //택배위치

    @ApiModelProperty(position = 4, example = "6")
    private int level;          //배송단계

    @ApiModelProperty(position = 5, example = "배송완료")
    private String details;     //배송상세정보
}
