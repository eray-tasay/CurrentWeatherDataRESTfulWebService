package com.eraytasay.weather.service.currentdata.lib.service;

import com.eraytasay.weather.service.currentdata.lib.dto.data.CurrentWeatherData;
import com.eraytasay.weather.service.currentdata.lib.dto.query.WeatherDataQueryWithCode;
import com.eraytasay.weather.service.currentdata.lib.dto.query.WeatherDataQueryWithCoordinates;
import com.eraytasay.weather.service.currentdata.lib.dto.query.WeatherDataQueryWithName;
import com.eraytasay.weather.service.currentdata.lib.service.consumer.openweather.CurrentWeatherDataConsumer;
import com.eraytasay.weather.service.currentdata.lib.service.consumer.openweather.GeocodingApiConsumer;
import org.springframework.stereotype.Service;

@Service
public class CurrentWeatherService {
    private final GeocodingApiConsumer m_geocodingApiConsumer;
    private final CurrentWeatherDataConsumer m_currentWeatherDataConsumer;

    public CurrentWeatherService(GeocodingApiConsumer geocodingApiConsumer, CurrentWeatherDataConsumer currentWeatherDataConsumer)
    {
        m_geocodingApiConsumer = geocodingApiConsumer;
        m_currentWeatherDataConsumer = currentWeatherDataConsumer;
    }

    public CurrentWeatherData getCurrentWeatherDataByCoordinates(WeatherDataQueryWithCoordinates query)
    {
        return m_currentWeatherDataConsumer.getCurrentWeatherDataByCoordinates(query);
    }

    public CurrentWeatherData getCurrentWeatherDataByName(WeatherDataQueryWithName query)
    {
        var coord = m_geocodingApiConsumer.getCoordinatesByName(query.name, query.appid);
        var queryWithCoordinates = new WeatherDataQueryWithCoordinates(query.appid, query.units, query.lang, coord.lat, coord.lon);

        return getCurrentWeatherDataByCoordinates(queryWithCoordinates);
    }

    public CurrentWeatherData getCurrentWeatherDataByCode(WeatherDataQueryWithCode query)
    {
        var coord = m_geocodingApiConsumer.getCoordinatesByName(query.name, query.appid);
        var queryWithCoordinates = new WeatherDataQueryWithCoordinates(query.appid, query.units, query.lang, coord.lat, coord.lon);

        return getCurrentWeatherDataByCoordinates(queryWithCoordinates);
    }
}
