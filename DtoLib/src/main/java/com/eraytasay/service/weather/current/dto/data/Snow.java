package com.eraytasay.service.weather.current.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Snow {
    @JsonProperty("1h")
    public double precipitation;
}
