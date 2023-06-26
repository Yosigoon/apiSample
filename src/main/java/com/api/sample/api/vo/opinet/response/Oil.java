package com.api.sample.api.vo.opinet.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Oil {
    @JsonProperty("PRODCD")
    private String PRODCD;          //제품구분
    @JsonProperty("PRICE")
    private double PRICE;           //평균가격(판매가격)
    @JsonProperty("DIFF")
    private int DIFF;               //전일대비 등락값
    @JsonProperty("SIGUNCD")
    private String SIGUNCD;         //시군코드
    @JsonProperty("SIGUNNM")
    private String SIGUNNM;         //시군구명
    @JsonProperty("AREA_CD")
    private String AREA_CD;         //지역구분
    @JsonProperty("AREA_NM")
    private String AREA_NM;         //지역명
    @JsonProperty("DATE")
    private String DATE;            //기준일자
    @JsonProperty("POLL_DIV_CD")
    private String POLL_DIV_CD;     //상표
    @JsonProperty("GPOLL_DIV_CD")
    private String GPOLL_DIV_CD;    //고속도로상표

    @JsonProperty("UNI_ID")
    private String UNI_ID;          //주유소코드
    @JsonProperty("OS_NM")
    private String OS_NM;           //상호
    @JsonProperty("VAN_ADR")
    private String VAN_ADR;         //지번주소
    @JsonProperty("NEW_ADR")
    private String NEW_ADR;         //도로명주소
    @JsonProperty("GIS_X_COOR")
    private String GIS_X_COOR;      //GIS X좌표(KATEC)
    @JsonProperty("GIS_Y_COOR")
    private String GIS_Y_COOR;      //GIS Y좌표(KATEC)
    @JsonProperty("TEL")
    private String TEL;             //전화번호
    @JsonProperty("LPG_YN")
    private String LPG_YN;          //업종구분 (N:주유소, Y:자동차충전소, C:주유소/충전소 겸업)

    @JsonProperty("MAINT_YN")
    private String MAINT_YN;        //경정비 시설 존재 여부
    @JsonProperty("CAR_WASH_YN")
    private String CAR_WASH_YN;     //세차장 존재 여부
    @JsonProperty("KPETRO_YN")
    private String KPETRO_YN;       //품질인증주유소 여부 (한국석유관리원의 품질인증프로그램 협약 업체)
    @JsonProperty("CVS_YN")
    private String CVS_YN;          //편의점 존재 여부
    @JsonProperty("OIL_PRICE")
    private OilPrice OIL_PRICE;

}
