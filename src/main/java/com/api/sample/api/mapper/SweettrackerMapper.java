package com.api.sample.api.mapper;

import com.api.sample.api.vo.sweettracker.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SweettrackerMapper {
    void insertInvoiceHistory(AddInvoiceRequestVO addInvoiceRequestVO);

    void updateInvoiceHistory(AddInvoiceResponseVO addInvoiceResponseVO);

    void insertTraceCommon(CallbackAddInvoiceRequestVO callbackAddInvoiceRequestVO);
}
