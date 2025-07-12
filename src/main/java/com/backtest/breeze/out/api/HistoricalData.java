package com.backtest.breeze.out.api;

import com.backtest.breeze.pojo.DataRequest;
import org.ta4j.core.BarSeries;

public interface HistoricalData {
    BarSeries get(DataRequest request);
}
