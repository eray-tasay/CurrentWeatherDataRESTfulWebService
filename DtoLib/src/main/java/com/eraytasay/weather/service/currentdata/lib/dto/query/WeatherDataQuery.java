package com.eraytasay.weather.service.currentdata.lib.dto.query;

public class WeatherDataQuery {
    public String appid;
    public String units;
    public String lang;

    public WeatherDataQuery(String appid, String units, String lang)
    {
        this.appid = appid;
        this.units = units;
        this.lang = lang;
    }
}
