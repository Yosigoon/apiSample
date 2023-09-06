package com.api.sample.api.controller;

import com.api.sample.api.service.OpinetService;
import com.api.sample.api.vo.opinet.request.*;
import com.api.sample.api.vo.opinet.response.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Set;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/jira")
public class JiraController {

    @PostMapping("/webhook")
    @ResponseBody
    public void jiraWebHook(HttpServletRequest request) throws IOException {
        BufferedReader input = new BufferedReader(new
                InputStreamReader(request.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String buffer;
        while ((buffer = input.readLine()) != null) {
            if (builder.length() > 0) {
                builder.append("\n");
            }
            builder.append(buffer);
        }
        log.info(builder.toString());
    }

    @GetMapping("/webhook")
    public void jiraWebHookGET(HttpServletRequest request) throws IOException {
        Set<String> keySet = request.getParameterMap().keySet();
        for(String key: keySet) {
            log.info("{}: {}", key, request.getParameter(key));
        }
    }
}






