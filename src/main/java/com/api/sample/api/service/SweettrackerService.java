package com.api.sample.api.service;

import com.api.sample.api.mapper.SweettrackerMapper;
import com.api.sample.api.vo.delivery.TraceCommonStateVO;
import com.api.sample.api.vo.sweettracker.*;
import com.api.sample.api.vo.sweettracker.fms.AddInvoiceListRequestParamVo;
import com.api.sample.api.vo.sweettracker.fms.AddInvoiceRequestParamVo;
import com.api.sample.enums.SweetCode;
import com.api.sample.feign.SweettrackerFeignClient;
import com.api.sample.util.DeliveryUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SweettrackerService {

    @Value("${sweet.callback_url}")
    private String callback_url;

    @Value("${sweet.callback_type}")
    private String callback_type;

    @Value("${sweet.tier}")
    private String tier;

    @Value("${sweet.key}")
    private String key;

    @Value("${sweet.type}")
    private String type;

    private final SweettrackerMapper sweettrackerMapper;

    private final SweettrackerFeignClient sweettrackerFeignClient;

    private final DeliveryGatewayService deliveryGatewayService;

    /**
     * 운송장 추적 요청 API
     * @param addInvoiceRequestParamVo
     * @return
     */
    public AddInvoiceResponseVO callAddInvoice(AddInvoiceRequestParamVo addInvoiceRequestParamVo) {
        AddInvoiceResponseVO addInvoiceResponseVO = new AddInvoiceResponseVO();
        //등록된 송장여부 체크
        TraceCommonStateVO traceCommonStateVO = deliveryGatewayService.selectTraceCommonState(addInvoiceRequestParamVo);
        if(ObjectUtils.isEmpty(traceCommonStateVO)){
            addInvoiceRequestParamVo.setRegId(SweetCode.REG_ID.getCode());
            addInvoiceRequestParamVo.setModId(SweetCode.MOD_ID.getCode());
            addInvoiceRequestParamVo.setLevel(SweetCode.배송준비.getCode());
            addInvoiceRequestParamVo.setDetails(SweetCode.배송준비.getMessage());

            //통계 및 최종 배송상태 조회용 저장
            deliveryGatewayService.insertTraceCommonState(addInvoiceRequestParamVo);

            String code = DeliveryUtil.getCompanyCode(addInvoiceRequestParamVo.getParcelCd());
            AddInvoiceRequestVO addInvoiceRequestVO = AddInvoiceRequestVO.builder()
                    .fid(code+"_"+addInvoiceRequestParamVo.getInvoiceNo())
                    .num(addInvoiceRequestParamVo.getInvoiceNo())
                    .code(code)
                    .callback_url(callback_url)
                    .callback_type(callback_type)
                    .tier(tier)
                    .key(key)
                    .type(type)
                    .build();

            //전송내역 저장
            addInvoiceRequestVO.setRegId(SweetCode.REG_ID.getCode());
            addInvoiceRequestVO.setModId(SweetCode.MOD_ID.getCode());
            sweettrackerMapper.insertInvoiceHistory(addInvoiceRequestVO);

            //스윗트래커 운송장 추적 요청 API 호출
            addInvoiceResponseVO = sweettrackerFeignClient.callAddInvoice(addInvoiceRequestVO);

            //전송내역 업데이트
            sweettrackerMapper.updateInvoiceHistory(addInvoiceResponseVO);
        }else{
            //기 등록된 송장일 경우 response 세팅
            addInvoiceResponseVO.setSuccess(false);
            addInvoiceResponseVO.setE_code(SweetCode.ERROR_0.getCode());
            addInvoiceResponseVO.setE_message(SweetCode.ERROR_0.getMessage());
        }
        return addInvoiceResponseVO;
    }

    /**
     * n건 운송장 추적 요청 API
     * @param addInvoiceListRequestParamVo
     * @return
     */
    public AddInvoiceListResponseVO callAddInvoiceList(AddInvoiceListRequestParamVo addInvoiceListRequestParamVo) {

        List<InvoiceRequestListVO> invoiceRequestList = new ArrayList<>();
        for (int i = 0; i < addInvoiceListRequestParamVo.getList().size(); i++) {
            invoiceRequestList.add(new InvoiceRequestListVO(addInvoiceListRequestParamVo.getList().get(i).getInvoiceNo(), addInvoiceListRequestParamVo.getList().get(i).getParcelCd()));
            //전송내역 저장
            //sweettrackerMapper.insertInvoiceListHistory(invoiceRequestList);
        }
        AddInvoiceListRequestVO addInvoiceListRequestVO = AddInvoiceListRequestVO.builder()
                .callback_url(callback_url)
                .callback_type(callback_type)
                .tier(tier)
                .key(key)
                .list(invoiceRequestList)
                .build();

        //스윗트래커 n건 운송장 추적 요청 API 호출
        AddInvoiceListResponseVO addInvoiceListResponseVO = sweettrackerFeignClient.callAddInvoiceList(addInvoiceListRequestVO);
        log.info("AddInvoiceListResponse: {}", addInvoiceListResponseVO);

        for (int i = 0; i < addInvoiceListResponseVO.getList().size(); i++) {
            //전송내역 업데이트
            //sweettrackerMapper.updateInvoiceListHistory(addInvoiceListResponseVO);
        }

        return addInvoiceListResponseVO;
    }

    /**
     * 운송장 추적정보 수신(callback)
     * @param callbackAddInvoiceRequestVO
     * @return
     */
    public CallbackAddInvoiceResponseVO callbackAddInvoice(CallbackAddInvoiceRequestVO callbackAddInvoiceRequestVO) {
        log.info("callbackAddInvoice: {}", callbackAddInvoiceRequestVO);
        callbackAddInvoiceRequestVO.setRegId(SweetCode.REG_ID.getCode());
        callbackAddInvoiceRequestVO.setModId(SweetCode.MOD_ID.getCode());
        callbackAddInvoiceRequestVO.setComcode(callbackAddInvoiceRequestVO.getFid().split("_")[0]);

        //배송추적 저장
        sweettrackerMapper.insertTraceCommon(callbackAddInvoiceRequestVO);

        //통계 및 최종 배송상태 업데이트
        deliveryGatewayService.updateTraceCommonState(callbackAddInvoiceRequestVO);

        return new CallbackAddInvoiceResponseVO(callbackAddInvoiceRequestVO);
    }


    /**
     * 지원택배사 조회
     * @return
     */
    public List<CompanyListVO> callCompanyList() {
        return sweettrackerFeignClient.callComplayList();
    }


}
