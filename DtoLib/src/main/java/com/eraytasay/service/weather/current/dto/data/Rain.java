package com.eraytasay.service.weather.current.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rain {
    @JsonProperty("1h")
    public double precipitation;
}
