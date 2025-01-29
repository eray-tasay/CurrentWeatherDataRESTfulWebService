package com.eraytasay.service.weather.current.dto.query;

public class WeatherDataQueryWithName extends WeatherDataQuery {
    public String name;

    public WeatherDataQueryWithName(String appid, String units, String lang, String name)
    {
        super(appid, units, lang);
        this.name = name;
    }
}
