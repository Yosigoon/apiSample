package com.api.sample.api.vo.sweettracker;

import com.api.sample.api.vo.CommonSystemVO;
import lombok.Data;

@Data
public class CallbackAddInvoiceRequestVO extends CommonSystemVO {
    private String secret_value;        //인증키(현재사용안함)
    private String fid;                 //식별값
    private String invoice_no;          //운송장번호
    private String time_trans;          //택배사 처리시간
    private String time_sweet;          //스윗트래커 등록시간
    private String where;               //택배 위치
    private String telno_office;        //사업소 기반 전화번호
    private String telno_man;           //배송기사 전화번호
    private String details;             //배송상세 정보
    private String recv_addr;           //수취인 주소
    private String recv_name;           //수취인 이름
    private String send_name;           //발신인 이름
    private String man;                 //배송기사 이름
    private String estmate;             //배송예정 시간
    private String comcode;             //택배사코드
}
