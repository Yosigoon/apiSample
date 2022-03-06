package com.api.sample.api.vo.sweettracker;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class AddInvoiceListRequestVO {
    private String callback_url;
    private String callback_type;
    private String tier;
    private String key;
    private List<InvoiceRequestListVO> list;
}
