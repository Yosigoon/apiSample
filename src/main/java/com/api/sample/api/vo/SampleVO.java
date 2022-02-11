package com.api.sample.api.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@ToString
public class SampleVO {

    @NotBlank
    private String sampleId;
    @NotNull
    private String sampleNm;

    private int no;

}
