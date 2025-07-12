package com.backtest.breeze.out.api;

import com.backtest.breeze.pojo.OHLCV;
import com.backtest.breeze.utils.DateTimeUtils;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBarSeries;
import org.ta4j.core.BaseBarSeriesBuilder;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class BaseHistoricalData implements HistoricalData {

    protected BarSeries getSeries(List<OHLCV> rawCandleData, long interval, TimeUnit timeUnit) {
        BaseBarSeries series = new BaseBarSeriesBuilder().build();
        for(OHLCV rawCandle : rawCandleData) {
            series.barBuilder()
                    .timePeriod(getDuration(interval, timeUnit))
                    .endTime(DateTimeUtils.getInstant(rawCandle.getDatetime()))
                    .openPrice(rawCandle.getOpen())
                    .highPrice(rawCandle.getHigh())
                    .lowPrice(rawCandle.getLow())
                    .closePrice(rawCandle.getClose())
                    .volume(rawCandle.getVolume())
                    .add();
        }
        return series;
    }

    protected Duration getDuration(long interval, TimeUnit unit) {
        return switch (unit) {
            case TimeUnit.SECONDS -> Duration.ofSeconds(interval);
            case  TimeUnit.MINUTES -> Duration.ofMinutes(interval);
            default -> null;
        };
    }
}
