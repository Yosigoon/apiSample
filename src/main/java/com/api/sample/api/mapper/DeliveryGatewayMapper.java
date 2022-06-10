package com.api.sample.api.mapper;

import com.api.sample.api.vo.delivery.TraceCommonStateVO;
import com.api.sample.api.vo.sweettracker.CallbackAddInvoiceRequestVO;
import com.api.sample.api.vo.sweettracker.fms.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeliveryGatewayMapper {

    int insertTraceCommonState(AddInvoiceRequestParamVo addInvoiceRequestParamVo);

    List<TraceCommonStateVO> selectTraceCommonStateList(TraceStateParamVO traceStateParamVO);

    int updateTraceCommonState(CallbackAddInvoiceRequestVO callbackAddInvoiceRequestVO);

    TraceCommonStateVO selectTraceCommonState(AddInvoiceRequestParamVo addInvoiceRequestParamVo);

    List<TraceListVO> selectTraceCommonList(TraceListParamVO traceListParamVO);

    List<String> selectInvoiceList(AddInvoiceListRequestParamVo addInvoiceListRequestParamVo);
}
