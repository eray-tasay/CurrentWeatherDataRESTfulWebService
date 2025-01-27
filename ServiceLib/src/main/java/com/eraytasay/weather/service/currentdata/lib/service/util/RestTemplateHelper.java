package com.eraytasay.weather.service.currentdata.lib.service.util;

import com.eraytasay.weather.service.currentdata.lib.dto.query.WeatherDataQuery;
import com.eraytasay.weather.service.currentdata.lib.dto.query.WeatherDataQueryWithCoordinates;

import java.util.HashMap;
import java.util.Map;

public final class RestTemplateHelper {
    private RestTemplateHelper()
    {}

    private static void putEntriesForBase(Map<String, Object> map, WeatherDataQuery weatherDataQuery)
    {
        if (weatherDataQuery.appid != null)
            map.put("appid", weatherDataQuery.appid);

        if (weatherDataQuery.lang != null)
            map.put("lang", weatherDataQuery.lang);

        if (weatherDataQuery.units != null)
            map.put("units", weatherDataQuery.units);
    }

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

        putEntriesForBase(result, weatherDataQueryWithCoordinates);

        result.put("lat", weatherDataQueryWithCoordinates.lat);
        result.put("lon", weatherDataQueryWithCoordinates.lon);

        return result;
    }
}
