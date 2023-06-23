package com.api.sample.api.vo.opinet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SigunResponseVO {
    private String RESULT;
    private List<SigunPriceList> OIL;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SigunPriceList {
        private String SIGUNCD; //시군구 구분코드
        private String SIGUNNM; //시군구명
        private String PRODCD;  //제품구분
        private String PRICE;   //평균가격
        private String DIFF;    //전일대비 등락값
    }
}
