package com.eraytasay.weather.service.currentdata.lib.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rain {
    @JsonProperty("1h")
    public double precipitation;
}
