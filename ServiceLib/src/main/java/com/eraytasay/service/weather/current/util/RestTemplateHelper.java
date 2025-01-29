package com.eraytasay.service.weather.current.util;

import com.eraytasay.service.weather.current.dto.query.WeatherDataQueryWithCoordinates;

import java.util.HashMap;
import java.util.Map;

public final class RestTemplateHelper {
    private RestTemplateHelper()
    {}

    public static String getUrl(String baseUrl, Map<String, Object> parameters)
    {
        if (parameters.isEmpty())
            return baseUrl;

        var sb = new StringBuilder(baseUrl);

        sb.append('?');
        for (var entry : parameters.entrySet()) {
            var key = entry.getKey();
            var value = entry.getValue();

            sb.append("%s=%s&".formatted(key, value));
        }

        return sb.substring(0, sb.length() - 1);
    }

    public static Map<String, Object> getUrlParameters(WeatherDataQueryWithCoordinates weatherDataQueryWithCoordinates)
    {
        var result = new HashMap<String, Object>();

        if (weatherDataQueryWithCoordinates.lang != null)
            result.put("lang", weatherDataQueryWithCoordinates.lang);

        if (weatherDataQueryWithCoordinates.units != null)
            result.put("units", weatherDataQueryWithCoordinates.units);

        result.put("appid", weatherDataQueryWithCoordinates.appid);
        result.put("lat", weatherDataQueryWithCoordinates.lat);
        result.put("lon", weatherDataQueryWithCoordinates.lon);

        return result;
    }
}
