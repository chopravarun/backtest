package com.backtest.breeze.out.impl;

import com.backtest.breeze.out.api.BaseHistoricalData;
import com.backtest.breeze.pojo.BreezeResponse;
import com.backtest.breeze.pojo.DataRequest;
import com.backtest.breeze.pojo.OHLCV;
import com.breeze.breezeconnect.BreezeConnect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ta4j.core.BarSeries;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class BreezeHistoricalData extends BaseHistoricalData {

    @Autowired
    private BreezeConnect bConn;

    @Autowired
    private ObjectMapper mapper;


    @Override
    public BarSeries get(DataRequest request) {
        JSONObject jsonResponse = bConn.getHistoricalDatav2(
                getBreezeInterval(request.getInterval(), request.getTimeUnit()),
                request.getFromDate(),
                request.getToDate(),
                request.getStockCode(),
                request.getExchange(),
                request.getProductType(),
                request.getExpiry(),
                request.getOptionType(),
                request.getStrike());
        List<OHLCV> rawCandleData = responseHandler(jsonResponse);
        return getSeries(rawCandleData, request.getInterval(), request.getTimeUnit());
    }

    private List<OHLCV> responseHandler(JSONObject rawResponse) {
        try {

            BreezeResponse responseObject = mapper.readValue(rawResponse.toString(), BreezeResponse.class);
            return responseObject.getOhlcv();
        } catch (JsonProcessingException e) {
            log.error("error when parsing response from api : {}", rawResponse.toString());
            log.error("error : {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    private String getBreezeInterval(long interval, TimeUnit unit) {
        return switch (unit) {
            case TimeUnit.SECONDS -> interval + "second";
            case TimeUnit.MINUTES -> interval + "minute";
            default -> "";
        };
    }
}
