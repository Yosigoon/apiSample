package com.api.sample.api.vo;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SampleVO {

    @NotBlank
    private String sampleId;
    @NotNull
    private String sampleNm;

    private int no;

}
