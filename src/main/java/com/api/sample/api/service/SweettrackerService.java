package com.api.sample.api.service;

import com.api.sample.api.mapper.SweettrackerMapper;
import com.api.sample.api.vo.delivery.TraceCommonStateVO;
import com.api.sample.api.vo.sweettracker.*;
import com.api.sample.api.vo.sweettracker.fms.AddInvoiceListRequestParamVo;
import com.api.sample.api.vo.sweettracker.fms.AddInvoiceRequestParamVo;
import com.api.sample.enums.SweetCode;
import com.api.sample.feign.SweettrackerFeignClient;
import com.api.sample.util.DeliveryUtil;
import com.google.common.collect.Lists;
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

    /**
     * n건 운송장 추적 요청 API
     * @param addInvoiceListRequestParamVo
     * @return
     */
    public void callAddInvoiceList(AddInvoiceListRequestParamVo addInvoiceListRequestParamVo) throws InterruptedException {
        int chunkSize = 500;
        List<String> invoiceListVO = deliveryGatewayService.selectInvoiceList(addInvoiceListRequestParamVo);
        List<List<String>> partition = Lists.partition(invoiceListVO, chunkSize);
        for (int i = 0; i < partition.size(); i++){
            AddInvoiceListRequestVO addInvoiceListRequestVO = new AddInvoiceListRequestVO();
            List<InvoiceRequestListVO> invoiceRequestList = new ArrayList<>();

            for(int j = 0; j < partition.get(i).size(); j++){
                String num = partition.get(i).get(j); //송장번호
                String code = DeliveryUtil.getCompanyCode(addInvoiceListRequestParamVo.getParcelCd()); //택배사코드 변환
                addInvoiceListRequestVO.setCallback_url(callback_url);
                addInvoiceListRequestVO.setCallback_type(callback_type);
                addInvoiceListRequestVO.setTier(tier);
                addInvoiceListRequestVO.setKey(key);
                invoiceRequestList.add(new InvoiceRequestListVO(num, addInvoiceListRequestParamVo.getParcelCd()));
                addInvoiceListRequestVO.setList(invoiceRequestList);

                AddInvoiceRequestVO addInvoiceRequestVO = AddInvoiceRequestVO.builder()
                        .fid(code + "_" + num)
                        .num(num)
                        .code(code)
                        .build();
                //전송내역 저장
                addInvoiceRequestVO.setRegId(SweetCode.REG_ID.getCode());
                addInvoiceRequestVO.setModId(SweetCode.MOD_ID.getCode());
                sweettrackerMapper.insertInvoiceHistory(addInvoiceRequestVO);
            }
            //스윗트래커 n건 운송장 추적 요청 API 호출
            AddInvoiceListResponseVO addInvoiceListResponseVO = sweettrackerFeignClient.callAddInvoiceList(addInvoiceListRequestVO);
            for(AddInvoiceResponseListVO vo : addInvoiceListResponseVO.getList()){
                AddInvoiceResponseVO addInvoiceResponseVO = new AddInvoiceResponseVO();
                addInvoiceResponseVO.setSuccess("true".equals(vo.getSuccess()));
                addInvoiceResponseVO.setFid(vo.getFid());
                addInvoiceResponseVO.setE_code(vo.getE_code());
                addInvoiceResponseVO.setE_message(vo.getE_message());
                //전송내역 업데이트
                sweettrackerMapper.updateInvoiceHistory(addInvoiceResponseVO);
            }
            Thread.sleep(2500);
        }
    }


}
