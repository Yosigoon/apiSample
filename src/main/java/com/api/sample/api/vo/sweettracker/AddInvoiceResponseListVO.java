package com.api.sample.api.vo.sweettracker;

import lombok.Data;

@Data
public class AddInvoiceResponseListVO {
    private String fid;         //식별 값
    private String num;         //운송장 번호
    private String success;     //식별 값
    private String e_code;      //에러메시지 코드
    private String e_message;   //에러메시지
}
