package com.eraytasay.service.weather.current.dto.query;

public class WeatherDataQueryWithCoordinates extends WeatherDataQuery {
    public double lat;
    public double lon;

    public WeatherDataQueryWithCoordinates(String appid, String units, String lang, double lat, double lon)
    {
        super(appid, units, lang);
        this.lat = lat;
        this.lon = lon;
    }
}
