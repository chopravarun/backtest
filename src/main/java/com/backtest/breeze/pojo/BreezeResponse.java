package com.backtest.breeze.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BreezeResponse {
    @JsonProperty("Status")
    private int Status;

    @JsonProperty("Error")
    private Object Error;

    @JsonProperty("Success")
    private List<OHLCV> ohlcv;

}

