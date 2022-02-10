package com.api.sample.api.service;

import com.api.sample.api.mapper.ApiSampleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiSampleService {

    @Autowired
    ApiSampleMapper apiSampleMapper;

    public String getDate(int no) {
        return apiSampleMapper.getDate(no);
    }
}
