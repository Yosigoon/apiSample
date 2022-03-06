package com.api.sample.feign;

import com.api.sample.api.vo.sweettracker.AddInvoiceListRequestVO;
import com.api.sample.api.vo.sweettracker.AddInvoiceListResponseVO;
import com.api.sample.api.vo.sweettracker.AddInvoiceRequestVO;
import com.api.sample.api.vo.sweettracker.AddInvoiceResponseVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name="delivery-gateway", url="${sweettracker.url}")
public interface SweettrackerFeignClient {

    @PostMapping(value = "/add_invoice", produces = "application/json")
    @ResponseBody
    AddInvoiceResponseVO callAddInvoice(AddInvoiceRequestVO addInvoiceRequestVO);

    @PostMapping(value = "/add_invoice_list", produces = "application/json")
    @ResponseBody
    AddInvoiceListResponseVO callAddInvoiceList(AddInvoiceListRequestVO addInvoiceListResponseVO);
}
