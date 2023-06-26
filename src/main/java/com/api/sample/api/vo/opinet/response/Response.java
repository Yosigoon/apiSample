package com.api.sample.api.vo.opinet.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class Response {

    @JsonProperty("RESULT")
    private Result result;

}
