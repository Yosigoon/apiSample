package com.api.sample.api.controller;

import com.api.sample.api.service.DeliveryGatewayService;
import com.api.sample.api.vo.delivery.TraceCommonStateVO;
import com.api.sample.api.vo.sweettracker.fms.AddInvoiceRequestParamVo;
import com.api.sample.api.vo.sweettracker.fms.TraceListParamVO;
import com.api.sample.api.vo.sweettracker.fms.TraceListVO;
import com.api.sample.api.vo.sweettracker.fms.TraceStateParamVO;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/trace")
public class DeliveryGatewayController {

    private final DeliveryGatewayService deliveryGatewayService;

    /**
     * 배송추적 단계별 리스트 조회
     * @param traceListParamVO
     * @return
     */
    @ApiOperation(value = "배송추적조회(팝업용)", response = TraceListVO.class, responseContainer = "List", notes = "invoiceNo: 송장번호, parcelCd: 택배사코드")
    @PostMapping("/popup")
    public List<TraceListVO> selectTraceCommonList(@RequestBody @Valid TraceListParamVO traceListParamVO){
        return deliveryGatewayService.selectTraceCommonList(traceListParamVO);
    }

    /**
     * 현 배송상태 조회 (n건)
     * @param traceStateParamVO
     * @return
     */
    @ApiOperation(value = "배송상태조회", response = TraceCommonStateVO.class, responseContainer = "List", notes = "배송상태조회 시 FMS검수패킹 조회날짜 8자리 전송")
    @PostMapping("/list")
    public List<TraceCommonStateVO> selectTraceCommonStateList(@RequestBody @Valid TraceStateParamVO traceStateParamVO){
        return deliveryGatewayService.selectTraceCommonStateList(traceStateParamVO);
    }

    /**
     * 현 배송상태 조회
     * @param addInvoiceRequestParamVo
     * @return
     */
    @ApiOperation(value = "현 배송상태 조회", hidden = true)
    @PostMapping("/state")
    public TraceCommonStateVO selectTraceCommonState(@RequestBody @Valid AddInvoiceRequestParamVo addInvoiceRequestParamVo){
        return deliveryGatewayService.selectTraceCommonState(addInvoiceRequestParamVo);
    }
}






