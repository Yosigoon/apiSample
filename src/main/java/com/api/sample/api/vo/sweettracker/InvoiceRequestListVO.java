package com.api.sample.api.vo.sweettracker;

import com.api.sample.util.DeliveryUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InvoiceRequestListVO {
    private String num;         //운송장번호(공백또는 "-"제거)
    private String code;        //배송사 코드
    private String fid;         //해당 건의 결과 전송에 쓰이는 식별 값

    public InvoiceRequestListVO(String num, String code){
        setNum(num);
        setCode(DeliveryUtil.getCompanyCode(code));
        setFid(getCode() + num);
    }
}
