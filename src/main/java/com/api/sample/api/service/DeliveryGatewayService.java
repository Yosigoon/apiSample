package com.api.sample.api.service;

import com.api.sample.api.mapper.DeliveryGatewayMapper;
import com.api.sample.api.vo.delivery.TraceCommonStateVO;
import com.api.sample.api.vo.sweettracker.CallbackAddInvoiceRequestVO;
import com.api.sample.api.vo.sweettracker.fms.AddInvoiceRequestParamVo;
import com.api.sample.api.vo.sweettracker.fms.TraceListParamVO;
import com.api.sample.api.vo.sweettracker.fms.TraceListVO;
import com.api.sample.api.vo.sweettracker.fms.TraceStateParamVO;
import com.api.sample.util.DeliveryUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryGatewayService {

    private final DeliveryGatewayMapper deliveryGatewayMapper;

    public int insertTraceCommonState(AddInvoiceRequestParamVo addInvoiceRequestParamVo) {
        return deliveryGatewayMapper.insertTraceCommonState(addInvoiceRequestParamVo);
    }

    public List<TraceCommonStateVO> selectTraceCommonStateList(TraceStateParamVO traceStateParamVO) {
        return deliveryGatewayMapper.selectTraceCommonStateList(traceStateParamVO);
    }

    public int updateTraceCommonState(CallbackAddInvoiceRequestVO callbackAddInvoiceRequestVO) {
        return deliveryGatewayMapper.updateTraceCommonState(callbackAddInvoiceRequestVO);
    }

    public TraceCommonStateVO selectTraceCommonState(AddInvoiceRequestParamVo addInvoiceRequestParamVo) {
        return deliveryGatewayMapper.selectTraceCommonState(addInvoiceRequestParamVo);
    }

    public List<TraceListVO> selectTraceCommonList(TraceListParamVO traceListParamVO) {
        traceListParamVO.setFid(DeliveryUtil.getCompanyCode(traceListParamVO.getParcelCd())+"_"+traceListParamVO.getInvoiceNo());
        return deliveryGatewayMapper.selectTraceCommonList(traceListParamVO);
    }
}
