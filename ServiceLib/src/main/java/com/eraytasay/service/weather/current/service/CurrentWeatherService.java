package com.eraytasay.service.weather.current.service;

import com.eraytasay.service.weather.current.dto.data.CurrentWeatherData;
import com.eraytasay.service.weather.current.dto.query.WeatherDataQuery;
import com.eraytasay.service.weather.current.dto.query.WeatherDataQueryWithCode;
import com.eraytasay.service.weather.current.dto.query.WeatherDataQueryWithCoordinates;
import com.eraytasay.service.weather.current.dto.query.WeatherDataQueryWithName;
import com.eraytasay.service.weather.current.consumer.openweather.CurrentWeatherDataConsumer;
import com.eraytasay.service.weather.current.consumer.openweather.GeocodingApiConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CurrentWeatherService {
    private final GeocodingApiConsumer m_geocodingApiConsumer;
    private final CurrentWeatherDataConsumer m_currentWeatherDataConsumer;

    @Value("${api.openweather.key}")
    private String apiKey;

    public CurrentWeatherService(GeocodingApiConsumer geocodingApiConsumer, CurrentWeatherDataConsumer currentWeatherDataConsumer)
    {
        m_geocodingApiConsumer = geocodingApiConsumer;
        m_currentWeatherDataConsumer = currentWeatherDataConsumer;
    }

    public CurrentWeatherData getCurrentWeatherDataByCoordinates(WeatherDataQueryWithCoordinates query)
    {
        setApiKey(query);

        return m_currentWeatherDataConsumer.getCurrentWeatherDataByCoordinates(query);
    }

    public CurrentWeatherData getCurrentWeatherDataByName(WeatherDataQueryWithName query)
    {
        setApiKey(query);

        var coord = m_geocodingApiConsumer.getCoordinatesByName(query.name, query.appid);
        var queryWithCoordinates = new WeatherDataQueryWithCoordinates(query.appid, query.units, query.lang, coord.lat, coord.lon);

        return getCurrentWeatherDataByCoordinates(queryWithCoordinates);
    }

    public CurrentWeatherData getCurrentWeatherDataByCode(WeatherDataQueryWithCode query)
    {
        setApiKey(query);

        var coord = m_geocodingApiConsumer.getCoordinatesByCode(query.code, query.appid);
        var queryWithCoordinates = new WeatherDataQueryWithCoordinates(query.appid, query.units, query.lang, coord.lat, coord.lon);

        return getCurrentWeatherDataByCoordinates(queryWithCoordinates);
    }

    private void setApiKey(WeatherDataQuery query)
    {
        if (query.appid == null || query.appid.isEmpty())
            query.appid = apiKey;
    }
}
