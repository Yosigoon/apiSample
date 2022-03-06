package com.api.sample.api.vo.sweettracker;

import lombok.Data;

import java.util.List;

@Data
public class AddInvoiceListResponseVO {
    private boolean success;                        //성공여부(true, false)
    private List<AddInvoiceResponseListVO> list;    //JSONArray
    private String e_code;                          //에러메시지 코드
    private String e_message;                       //에러메시지
}
