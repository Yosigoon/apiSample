package com.api.sample.enums;

import lombok.Getter;

@Getter
public enum SweetCode {

    SUCCESS("true", "성공"),
    FAIL("false", "실패"),

    //공통시스템
    REG_ID("SWEET_REG_API", "등록자ID"),
    MOD_ID("SWEET_MOD_API", "수정자ID"),

    //배송단계(LEVEL)
    배송준비("1", "배송준비"),
    집화완료("2", "집화완료"),
    배송진행("3", "배송진행"),
    지점도착("4", "지점도착"),
    배송출발("5", "배송출발"),
    배송완료("6", "배송완료"),
    배송스캔오류("-99", "배송준비"),

    //중복코드 자체생성
    ERROR_0("0", "이미 등록된 송장번호가 있습니다"),

    //운송장 추적 에러
    ERROR_1("1", "필수 파라메터가 빈 값이거나 없음"),
    ERROR_2("2", "운송장번호 유효성 검사 실패"),
    ERROR_4("4", "지원하지 않는 택배사 코드"),
    ERROR_99("99", "시스템 오류");




    private String code;
    private String message;

    SweetCode(String code, String message) {
        this.code = code;
        this.message= message;
    }
}
