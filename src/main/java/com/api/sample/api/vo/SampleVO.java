package com.api.sample.api.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SampleVO {

    @NotBlank
    private String sampleId;
    @NotNull
    private String sampleNm;
    private int no;

}
