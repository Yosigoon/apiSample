package com.api.sample.api.service;

import com.api.sample.api.mapper.ApiSampleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApiSampleService {

    private final ApiSampleMapper apiSampleMapper;

    public String getDate(int no) {
        return apiSampleMapper.getDate(no);
    }
}
