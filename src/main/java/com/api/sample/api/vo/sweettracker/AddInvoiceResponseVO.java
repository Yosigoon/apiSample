package com.api.sample.api.vo.sweettracker;

import lombok.Data;

@Data
public class AddInvoiceResponseVO {
    private boolean success;    //성공여부(true, false)
    private String num;         //운송장 번호
    private String fid;         //식별 값
    private String e_code;      //에러메시지 코드
    private String e_message;   //에러메시지
}
