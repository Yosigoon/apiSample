package com.api.sample.enums;

import lombok.Getter;

@Getter
public enum OpinetCode {
    XML("xml", "xml"),
    JSON("json", "json"),
    고급휘발유("B034", "고급휘발유"),
    보통휘발유("B027", "보통휘발유"),
    자동차경유("D047", "자동차경유"),
    실내등유("C004", "실내등유"),
    자동차부탄("K015", "자동차부탄"),

    SK에너지("SKE", "SK에너지"),
    GS칼텍스("GSC", "GS칼텍스"),
    현대오일뱅크("HDO", "현대오일뱅크"),
    SOIL("SOL", "S-OIL"),
    RTO("RTO", "자영알뜰"),
    RTX("RTX", "고속도로알뜰"),
    농협알뜰("NHO", "농협알뜰"),
    ETC("ETC", "자가상표");


    private String code;
    private String message;

    OpinetCode(String code, String message) {
        this.code = code;
        this.message= message;
    }
}
