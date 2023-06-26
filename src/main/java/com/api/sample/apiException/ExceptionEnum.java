package com.api.sample.apiException;


import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum ExceptionEnum {
    RUNTIME_EXCEPTION(HttpStatus.OK, "E0001", "RUNTIME_EXCEPTION"),
    ACCESS_DENIED_EXCEPTION(HttpStatus.OK, "E0002", "ACCESS_DENIED_EXCEPTION"),
    INTERNAL_SERVER_ERROR(HttpStatus.OK, "E0003", "INTERNAL_SERVER_ERROR"),
    SECURITY_01(HttpStatus.OK, "S0001", "권한이 없습니다."),
    VALIDATION_EXCEPTION(HttpStatus.OK, "V0001", "VALIDATION_EXCEPTION"),
    NOBODY_EXCEPTION(HttpStatus.OK, "N0001", "잘못된 호출입니다.");

    private final HttpStatus status;
    private final String code;
    private String message;

    ExceptionEnum(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
