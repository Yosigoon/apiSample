package com.api.sample.api.vo.sweettracker.fms;

import lombok.Data;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
public class AddInvoiceListRequestParamVo {
    @NotEmpty @Valid
    private List<AddInvoiceListVO> list;

    @Data
    public static class AddInvoiceListVO{
        @NotBlank
        private String invoiceNo; //FMS에서 전달받은 송장번호
        @NotBlank
        private String parcelCd;  //FMS에서 전달받은 택배사코드명
    }
}
