package com.eraytasay.service.weather.current.dal;

import com.eraytasay.service.weather.current.entity.CurrentWeatherDataApiQuery;
import com.eraytasay.service.weather.current.entity.GeocodingQuery;
import com.eraytasay.service.weather.current.entity.ApiKeyQuery;
import com.eraytasay.service.weather.current.repository.ICurrentWeatherDataApiQueryRepository;
import com.eraytasay.service.weather.current.repository.IGeocodingQueryRepository;
import com.eraytasay.service.weather.current.repository.IApiKeyQueryRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CurrentWeatherServiceHelper {
    private final IGeocodingQueryRepository m_geocodingQueryRepository;
    private final ICurrentWeatherDataApiQueryRepository m_currentWeatherDataApiQueryRepository;
    private final IApiKeyQueryRepository m_apiKeyQueryRepository;

    public CurrentWeatherServiceHelper(IGeocodingQueryRepository geocodingQueryRepository, ICurrentWeatherDataApiQueryRepository currentWeatherDataApiQueryRepository, IApiKeyQueryRepository apiKeyQueryRepository)
    {
        m_geocodingQueryRepository = geocodingQueryRepository;
        m_currentWeatherDataApiQueryRepository = currentWeatherDataApiQueryRepository;
        m_apiKeyQueryRepository = apiKeyQueryRepository;
    }

    public ApiKeyQuery saveApiKeyQuery(ApiKeyQuery apiKeyQuery)
    {
        return m_apiKeyQueryRepository.save(apiKeyQuery);
    }

    public Optional<ApiKeyQuery> findApiKeyQueryByApiKey(String apiKey)
    {
        return m_apiKeyQueryRepository.findByApiKey(apiKey);
    }

    public void incrementApiKeyQueryCountByKey(String apiKey)
    {
        m_apiKeyQueryRepository.incrementCountByApiKey(apiKey);
    }

    public boolean existsApiKeyQueryByApiKey(String apiKey)
    {
        return m_apiKeyQueryRepository.existsByApiKey(apiKey);
    }

    public GeocodingQuery saveGeocodingQuery(GeocodingQuery geocodingQuery)
    {
        return m_geocodingQueryRepository.save(geocodingQuery);
    }

    public CurrentWeatherDataApiQuery saveCurrentWeatherDataApiQuery(CurrentWeatherDataApiQuery currentWeatherDataApiQuery)
    {
        return m_currentWeatherDataApiQueryRepository.save(currentWeatherDataApiQuery);
    }
}
