package com.eraytasay.weather.service.currentdata.lib.dto.data;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class CurrentWeatherData {
    public Coord coord;
    public List<Weather> weather;
    public String base;
    public Main main;
    public int visibility;
    public Wind wind;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Rain rain;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Snow snow;
    public Clouds clouds;
    public long dt;
    public Sys sys;
    public int timezone;
    public int id;
    public String name;
    public int cod;
}
