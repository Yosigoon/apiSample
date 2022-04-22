package com.api.sample.feign;

import com.api.sample.api.vo.sweettracker.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@FeignClient(name="delivery-gateway", url="${sweettracker.url}")
public interface SweettrackerFeignClient {

    @PostMapping(value = "/add_invoice", produces = "application/json")
    AddInvoiceResponseVO callAddInvoice(AddInvoiceRequestVO addInvoiceRequestVO);

    @PostMapping(value = "/add_invoice_list", produces = "application/json")
    AddInvoiceListResponseVO callAddInvoiceList(AddInvoiceListRequestVO addInvoiceListResponseVO);

    @GetMapping(value = "/companylist")
    List<CompanyListVO> callComplayList();
}
