package com.backtest.breeze.pojo;

import lombok.Data;

@Data
public class OHLCV {
    private double volume;
    private String datetime;
    private double high;
    private double low;
    private String exchange_code;
    private double close;
    private String stock_code;
    private double open;
}
