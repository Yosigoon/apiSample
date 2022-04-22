package com.api.sample.api.vo.sweettracker;

import com.api.sample.api.vo.CommonSystemVO;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddInvoiceRequestVO extends CommonSystemVO {
    private String num;             //운송장번호(공백또는 "-"제거)
    private String code;            //배송사 코드
    private String fid;             //해당 건의 결과 전송에 쓰이는 식별 값 ( 유니크한 값 )
    private String callback_url;    //결과를 전달받을 URL
    private String callback_type;   //callback Response type (map,json,xml)
    private String tier;            //발급받은 tier
    private String key;             //발급받은 key
    private String type;            //Response type (json/xml) : 기본json
}
