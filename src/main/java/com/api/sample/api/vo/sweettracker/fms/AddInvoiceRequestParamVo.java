package com.api.sample.api.vo.sweettracker.fms;

import com.api.sample.api.vo.CommonSystemVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class AddInvoiceRequestParamVo extends CommonSystemVO {
    @ApiModelProperty(position = 1, example = "00001", required = true)
    @NotBlank
    private String cstCd;       //고객사코드

    @ApiModelProperty(position = 2, example = "YI01", required = true)
    @NotBlank
    private String whCd;        //센터코드

    @ApiModelProperty(position = 3, example = "552861755050", required = true)
    @NotBlank
    private String invoiceNo;   //송장번호

    @ApiModelProperty(position = 4, example = "HANJIN", required = true)
    @NotBlank
    private String parcelCd;    //택배사코드

}
