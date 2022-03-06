package com.api.sample.api.service;

import com.api.sample.api.mapper.SweettrackerMapper;
import com.api.sample.api.vo.sweettracker.*;
import com.api.sample.api.vo.sweettracker.fms.AddInvoiceListRequestParamVo;
import com.api.sample.api.vo.sweettracker.fms.AddInvoiceRequestParamVo;
import com.api.sample.feign.SweettrackerFeignClient;
import com.api.sample.util.DeliveryUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
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

    @Autowired
    SweettrackerMapper sweettrackerMapper;

    @Autowired
    SweettrackerFeignClient sweettrackerFeignClient;

    /**
     * 운송장 추적 요청 API
     * @param addInvoiceRequestParamVo
     * @return
     */
    public AddInvoiceResponseVO callAddInvoice(AddInvoiceRequestParamVo addInvoiceRequestParamVo) {
        String code = DeliveryUtil.getCompanyCode(addInvoiceRequestParamVo.getParcelCd());
        AddInvoiceRequestVO addInvoiceRequestVO = AddInvoiceRequestVO.builder()
                .num(addInvoiceRequestParamVo.getInvoiceNo())
                .code(code)
                .fid(code + addInvoiceRequestParamVo.getInvoiceNo())
                .callback_url(callback_url)
                .callback_type(callback_type)
                .tier(tier)
                .key(key)
                .type(type)
                .build();

        //전송내역 저장I
        sweettrackerMapper.insertInvoiceHistory(addInvoiceRequestVO);

        //스윗트래커 운송장 추적 요청 API 호출
        AddInvoiceResponseVO addInvoiceResponseVO = sweettrackerFeignClient.callAddInvoice(addInvoiceRequestVO);

        //전송내역 업데이트
        sweettrackerMapper.updateInvoiceHistory(addInvoiceResponseVO);

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
            sweettrackerMapper.insertInvoiceListHistory(invoiceRequestList);
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
            sweettrackerMapper.updateInvoiceListHistory(addInvoiceListResponseVO);
        }

        return addInvoiceListResponseVO;
    }


}
