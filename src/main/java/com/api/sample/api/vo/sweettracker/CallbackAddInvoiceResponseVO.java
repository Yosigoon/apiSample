package com.api.sample.api.vo.sweettracker;

import lombok.Data;

@Data
public class CallbackAddInvoiceResponseVO {
    private boolean code = true;
    private String message = "success";

    public CallbackAddInvoiceResponseVO(CallbackAddInvoiceRequestVO callbackAddInvoiceRequestVO) {
        if("-99".equals(callbackAddInvoiceRequestVO.getLevel())){
            setCode(false);
            setMessage("fail");
        }
    }
}
