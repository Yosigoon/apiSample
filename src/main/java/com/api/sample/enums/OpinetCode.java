package com.api.sample.enums;

import lombok.Getter;

@Getter
public enum OpinetCode {
    XML("xml", "xml"),
    고급휘발유("B034", "고급휘발유"),
    보통휘발유("B027", "보통휘발유"),
    자동차경유("D047", "자동차경유"),
    실내등유("C004", "실내등유"),
    자동차부탄("K015", "자동차부탄");

    private String code;
    private String message;

    OpinetCode(String code, String message) {
        this.code = code;
        this.message= message;
    }
}
