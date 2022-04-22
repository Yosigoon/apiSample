package com.api.sample.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeliveryUtil {

    /**
     * 택배사코드 추출
     * @param parcelCd
     * CJ대한통운 : 04
     * 한진택배 : 05
     * 롯데택배 : 08
     * 우체국택배 : 01
     * DHL : 13
     * UPS : 14
     * USPS : 26
     * CU 편의점택배 : 46
     * GS Postbox 택배 : 24
     * LX판토스 : 37
     * 부릉 : 110
     * @return
     */
    public static String getCompanyCode(String parcelCd){
        String comcode = "";
        if("CJ".equals(parcelCd)){
            comcode = "04";
        }else if("HANJIN".equals(parcelCd)){
            comcode = "05";
        }else if("LOTTE".equals(parcelCd)){
            comcode = "08";
        }else if("CHAINLOGIS".equals(parcelCd)){
            comcode = "89";
        }else if("EPOST".equals(parcelCd)){
            comcode = "01";
        }else if("KDEXP".equals(parcelCd)){
            comcode = "23";
        }else if("UPS".equals(parcelCd)){
            comcode = "14";
        }
        return comcode;
    }
}
