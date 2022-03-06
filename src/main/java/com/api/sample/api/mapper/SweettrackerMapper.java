package com.api.sample.api.mapper;

import com.api.sample.api.vo.sweettracker.AddInvoiceListResponseVO;
import com.api.sample.api.vo.sweettracker.AddInvoiceRequestVO;
import com.api.sample.api.vo.sweettracker.AddInvoiceResponseVO;
import com.api.sample.api.vo.sweettracker.InvoiceRequestListVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SweettrackerMapper {
    void insertInvoiceHistory(AddInvoiceRequestVO addInvoiceRequestVO);

    void updateInvoiceHistory(AddInvoiceResponseVO addInvoiceResponseVO);

    void insertInvoiceListHistory(List<InvoiceRequestListVO> invoiceRequestList);

    void updateInvoiceListHistory(AddInvoiceListResponseVO addInvoiceListResponseVO);
}
