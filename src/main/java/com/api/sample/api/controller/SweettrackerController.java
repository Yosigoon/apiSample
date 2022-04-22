package com.api.sample.api.controller;

import com.api.sample.api.service.SweettrackerService;
import com.api.sample.api.vo.sweettracker.*;
import com.api.sample.api.vo.sweettracker.fms.AddInvoiceListRequestParamVo;
import com.api.sample.api.vo.sweettracker.fms.AddInvoiceRequestParamVo;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/sweet")
public class SweettrackerController {

    private final SweettrackerService sweettrackerService;

    /**
     * 운송장 추적 요청 API
     * @param addInvoiceRequestParamVo
     * @return
     */
    @ApiOperation(value = "운송장 추적 요청(검수패킹 후 전송)", response = AddInvoiceResponseVO.class, notes = "success 값이 true 일 경우 e_code, e_message 값은 null")
    @PostMapping("/add_invoice")
    public AddInvoiceResponseVO callAddInvoice(@RequestBody @Valid AddInvoiceRequestParamVo addInvoiceRequestParamVo) {
        return sweettrackerService.callAddInvoice(addInvoiceRequestParamVo);
    }


    /**
     * n건 운송장 추적 요청 API
     * @param addInvoiceListRequestParamVo
     * @return
     */
    @ApiOperation(value = "n건 운송장 추적 요청", hidden = true)
    @PostMapping("/add_invoice_list")
    public AddInvoiceListResponseVO callAddInvoiceList(@RequestBody @Valid AddInvoiceListRequestParamVo addInvoiceListRequestParamVo) {
        return sweettrackerService.callAddInvoiceList(addInvoiceListRequestParamVo);
    }


    /**
     * 운송장 추적정보 수신(callback)
     * @param callbackAddInvoiceRequestVO
     * @return
     */
    @ApiOperation(value = "callback", hidden = true)
    @PostMapping("/callback")
    public CallbackAddInvoiceResponseVO callbackAddInvoice(@RequestBody CallbackAddInvoiceRequestVO callbackAddInvoiceRequestVO) {
        return sweettrackerService.callbackAddInvoice(callbackAddInvoiceRequestVO);
    }


    /**
     * 지원택배사 조회
     * @return
     */
    @ApiOperation(value = "지원택배사 조회")
    @GetMapping("/company_list")
    public List<CompanyListVO> callCompanyList(){
        return sweettrackerService.callCompanyList();
    }
}






