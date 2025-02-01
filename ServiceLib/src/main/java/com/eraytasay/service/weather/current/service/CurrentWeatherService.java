package com.eraytasay.service.weather.current.service;

import com.eraytasay.service.weather.current.consumer.openweather.CurrentWeatherDataConsumer;
import com.eraytasay.service.weather.current.consumer.openweather.GeocodingApiConsumer;
import com.eraytasay.service.weather.current.dal.CurrentWeatherServiceHelper;
import com.eraytasay.service.weather.current.dto.data.CurrentWeatherData;
import com.eraytasay.service.weather.current.dto.query.WeatherDataQuery;
import com.eraytasay.service.weather.current.dto.query.WeatherDataQueryWithCode;
import com.eraytasay.service.weather.current.dto.query.WeatherDataQueryWithCoordinates;
import com.eraytasay.service.weather.current.dto.query.WeatherDataQueryWithName;
import com.eraytasay.service.weather.current.entity.ApiKeyQuery;
import com.eraytasay.service.weather.current.entity.CurrentWeatherDataApiQuery;
import com.eraytasay.service.weather.current.entity.GeocodingQuery;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class CurrentWeatherService {
    private final CurrentWeatherServiceHelper m_currentWeatherServiceHelper;
    private final GeocodingApiConsumer m_geocodingApiConsumer;
    private final CurrentWeatherDataConsumer m_currentWeatherDataConsumer;
    private final TransactionTemplate m_transactionTemplate;

    @Value("${api.openweather.apikey}")
    private String m_defaultApiKey;

    public CurrentWeatherService(CurrentWeatherServiceHelper currentWeatherServiceHelper, GeocodingApiConsumer geocodingApiConsumer,
                                 CurrentWeatherDataConsumer currentWeatherDataConsumer, TransactionTemplate transactionTemplate)
    {
        m_currentWeatherServiceHelper = currentWeatherServiceHelper;
        m_geocodingApiConsumer = geocodingApiConsumer;
        m_currentWeatherDataConsumer = currentWeatherDataConsumer;
        m_transactionTemplate = transactionTemplate;
    }

    public CurrentWeatherData getCurrentWeatherDataByCoordinates(WeatherDataQueryWithCoordinates query)
    {
        setDefaultApiKey(query);
        saveCurrentWeatherDataApiQueryTransaction(query);
        return m_currentWeatherDataConsumer.getCurrentWeatherDataByCoordinates(query);
    }

    public CurrentWeatherData getCurrentWeatherDataByName(WeatherDataQueryWithName query)
    {
        setDefaultApiKey(query);
        saveGeocodingQueryTransaction(query);

        var coord = m_geocodingApiConsumer.getCoordinatesByName(query.name, query.appid);
        var queryWithCoordinates = new WeatherDataQueryWithCoordinates(query.appid, query.units, query.lang, coord.lat, coord.lon);

        saveCurrentWeatherDataApiQueryTransaction(queryWithCoordinates);
        return m_currentWeatherDataConsumer.getCurrentWeatherDataByCoordinates(queryWithCoordinates);
    }

    public CurrentWeatherData getCurrentWeatherDataByCode(WeatherDataQueryWithCode query)
    {
        setDefaultApiKey(query);
        saveGeocodingQueryTransaction(query);

        var coord = m_geocodingApiConsumer.getCoordinatesByCode(query.code, query.appid);
        var queryWithCoordinates = new WeatherDataQueryWithCoordinates(query.appid, query.units, query.lang, coord.lat, coord.lon);

        saveCurrentWeatherDataApiQueryTransaction(queryWithCoordinates);
        return m_currentWeatherDataConsumer.getCurrentWeatherDataByCoordinates(queryWithCoordinates);
    }

    private GeocodingQuery saveGeocodingQueryTransactionCallback(TransactionStatus transactionStatus,
                                                                 WeatherDataQuery query)
    {
        ApiKeyQuery apiKeyQuery;

        if (!m_currentWeatherServiceHelper.existsApiKeyQueryByApiKey(query.appid))
            apiKeyQuery = saveApiKeyQuery(query.appid);
        else
            apiKeyQuery = m_currentWeatherServiceHelper.findApiKeyQueryByApiKey(query.appid).get();

        var res = saveGeocodingQueryTransaction(apiKeyQuery, apiKeyQuery.count + 1);
        m_currentWeatherServiceHelper.incrementApiKeyQueryCountByKey(query.appid);

        return res;
    }

    private GeocodingQuery saveGeocodingQueryTransaction(WeatherDataQuery query)
    {
        return m_transactionTemplate.execute(status -> saveGeocodingQueryTransactionCallback(status, query));
    }

    private CurrentWeatherDataApiQuery saveCurrentWeatherDataApiQueryTransaction(WeatherDataQuery query)
    {
        return m_transactionTemplate.execute(status -> saveCurrentWeatherDataApiQueryTransactionCallback(status, query));
    }

    private CurrentWeatherDataApiQuery saveCurrentWeatherDataApiQueryTransactionCallback(TransactionStatus status,
                                                                                         WeatherDataQuery query)
    {
        ApiKeyQuery apiKeyQuery;

        if (!m_currentWeatherServiceHelper.existsApiKeyQueryByApiKey(query.appid))
            apiKeyQuery = saveApiKeyQuery(query.appid);
        else
            apiKeyQuery = m_currentWeatherServiceHelper.findApiKeyQueryByApiKey(query.appid).get();

        var res = saveCurrentWeatherDataApiQuery(apiKeyQuery, apiKeyQuery.count + 1);
        m_currentWeatherServiceHelper.incrementApiKeyQueryCountByKey(query.appid);

        return res;
    }

    private void setDefaultApiKey(WeatherDataQuery query)
    {
        if (query.appid == null || query.appid.isEmpty())
            query.appid = m_defaultApiKey;
    }

    private ApiKeyQuery saveApiKeyQuery(String apiKey)
    {
        var apiKeyQuery = new ApiKeyQuery();

        apiKeyQuery.apiKey = apiKey;
        return m_currentWeatherServiceHelper.saveApiKeyQuery(apiKeyQuery);
    }

    private CurrentWeatherDataApiQuery saveCurrentWeatherDataApiQuery(ApiKeyQuery apiKeyQuery, int count)
    {
        var currentWeatherDataApiQuery = new CurrentWeatherDataApiQuery();

        currentWeatherDataApiQuery.apiKeyQuery = apiKeyQuery;
        currentWeatherDataApiQuery.count = count;
        return m_currentWeatherServiceHelper.saveCurrentWeatherDataApiQuery(currentWeatherDataApiQuery);
    }

    private GeocodingQuery saveGeocodingQueryTransaction(ApiKeyQuery apiKeyQuery, int count)
    {
        var geocodingQuery = new GeocodingQuery();

        geocodingQuery.apiKeyQuery = apiKeyQuery;
        geocodingQuery.count = count;
        m_currentWeatherServiceHelper.saveGeocodingQuery(geocodingQuery);

        return geocodingQuery;
    }
}
