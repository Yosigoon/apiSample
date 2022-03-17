package com.api.sample.api.controller;

import com.api.sample.api.service.SweettrackerService;
import com.api.sample.api.vo.sweettracker.AddInvoiceListResponseVO;
import com.api.sample.api.vo.sweettracker.AddInvoiceResponseVO;
import com.api.sample.api.vo.sweettracker.CallbackAddInvoiceRequestVO;
import com.api.sample.api.vo.sweettracker.CallbackAddInvoiceResponseVO;
import com.api.sample.api.vo.sweettracker.fms.AddInvoiceListRequestParamVo;
import com.api.sample.api.vo.sweettracker.fms.AddInvoiceRequestParamVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SweettrackerController {

    private final SweettrackerService sweettrackerService;

    /**
     * 운송장 추적 요청 API
     * @param addInvoiceRequestParamVo
     */
    @PostMapping("/add_invoice")
    public AddInvoiceResponseVO callAddInvoice(@RequestBody @Valid AddInvoiceRequestParamVo addInvoiceRequestParamVo){
        return sweettrackerService.callAddInvoice(addInvoiceRequestParamVo);
    }


    /**
     * n건 운송장 추적 요청 API
     * @param addInvoiceListRequestParamVo
     * @return
     */
    @PostMapping("/add_invoice_list")
    public AddInvoiceListResponseVO callAddInvoiceList(@RequestBody @Valid AddInvoiceListRequestParamVo addInvoiceListRequestParamVo){
        return sweettrackerService.callAddInvoiceList(addInvoiceListRequestParamVo);
    }


    /**
     * 운송장 추적정보 수신(callback)
     * @param callbackAddInvoiceRequestVO
     * @return
     */
    @PostMapping("/invoice/callback")
    public CallbackAddInvoiceResponseVO callbackAddInvoice(CallbackAddInvoiceRequestVO callbackAddInvoiceRequestVO){
        //TODO 통합 배송조회 테이블 저장 등등...처리하기
        log.info("CallbackAddInvoiceRequestVO: {}", callbackAddInvoiceRequestVO);
        return new CallbackAddInvoiceResponseVO(callbackAddInvoiceRequestVO);
    }
}






