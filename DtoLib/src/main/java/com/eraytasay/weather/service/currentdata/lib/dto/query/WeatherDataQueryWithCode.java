package com.eraytasay.weather.service.currentdata.lib.dto.query;

public class WeatherDataQueryWithCode extends WeatherDataQuery {
    public String code;

    public WeatherDataQueryWithCode(String appid, String units, String lang, String code)
    {
        super(appid, units, lang);
        this.code = code;
    }
}
