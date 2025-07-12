package com.backtest.breeze.out.impl;

import com.backtest.breeze.out.api.DataRequestTemplate;
import com.backtest.breeze.pojo.DataRequest;

public class IndexDataRequestTemplate implements DataRequestTemplate {

    @Override
    public DataRequest get() {
        return DataRequest.builder()
                .productType("")
                .expiry("")
                .optionType("others")
                .strike("0")
                .build();
    }
}
