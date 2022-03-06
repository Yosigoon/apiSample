package com.api.sample.api.vo.sweettracker.fms;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class AddInvoiceRequestParamVo {
    @NotBlank
    private String invoiceNo;       //FMS에서 전달받은 송장번호
    @NotBlank
    private String parcelCd;        //FMS에서 전달받은 택배사코드명

}
