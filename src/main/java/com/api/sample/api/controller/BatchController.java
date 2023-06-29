package com.api.sample.api.controller;

import com.api.sample.api.service.BatchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/opinet/batch")
public class BatchController {

    private final BatchService batchService;

    @GetMapping("/myOilInfo")
    public void myOilInfo() throws JsonProcessingException {
        batchService.myOilInfo();
    }
}
