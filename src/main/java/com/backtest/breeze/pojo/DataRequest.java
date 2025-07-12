package com.backtest.breeze.pojo;

import lombok.*;

import java.util.concurrent.TimeUnit;

@Data
@EqualsAndHashCode
@ToString
@Builder(toBuilder = true)
public class DataRequest {
    private long interval;
    private TimeUnit timeUnit;
    private String fromDate;
    private String toDate;
    private String stockCode;
    private String exchange;
    private String productType;
    private String expiry;
    private String optionType;
    private String strike;
}
