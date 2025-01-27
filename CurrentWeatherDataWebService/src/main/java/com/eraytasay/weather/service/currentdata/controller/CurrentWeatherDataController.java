package com.eraytasay.weather.service.currentdata.controller;

import com.eraytasay.weather.service.currentdata.dto.NoWeatherDataDto;
import com.eraytasay.weather.service.currentdata.lib.dto.data.CurrentWeatherData;
import com.eraytasay.weather.service.currentdata.lib.dto.query.WeatherDataQueryWithCode;
import com.eraytasay.weather.service.currentdata.lib.dto.query.WeatherDataQueryWithCoordinates;
import com.eraytasay.weather.service.currentdata.lib.dto.query.WeatherDataQueryWithName;
import com.eraytasay.weather.service.currentdata.lib.service.CurrentWeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/current_weather_data")
public class CurrentWeatherDataController {
    private final CurrentWeatherService m_currentWeatherService;

    public CurrentWeatherDataController(CurrentWeatherService currentWeatherService)
    {
        m_currentWeatherService = currentWeatherService;
    }

    @GetMapping("coord")
    public CurrentWeatherData getCurrentWeatherDataByCoordinates(@RequestParam double lat, @RequestParam double lon,
                                                                 @RequestParam(required = false) String appid,
                                                                 @RequestParam(required = false) String units,
                                                                 @RequestParam(required = false) String lang)
    {
       var queryWithCoordinates = new WeatherDataQueryWithCoordinates(appid, units, lang, lat, lon);

        return m_currentWeatherService.getCurrentWeatherDataByCoordinates(queryWithCoordinates);
    }

    @GetMapping("name")
    public ResponseEntity<Object> getCurrentWeatherDataByName(@RequestParam String name,
                                                         @RequestParam(required = false) String appid,
                                                         @RequestParam(required = false) String units,
                                                         @RequestParam(required = false) String lang)
    {
        var queryWithName = new WeatherDataQueryWithName(appid, units, lang, name);

        try {
            var data = m_currentWeatherService.getCurrentWeatherDataByName(queryWithName);

            return ResponseEntity.ok(data);
        }
        catch (RuntimeException ex) {
            return ResponseEntity.ok(new NoWeatherDataDto(ex.getMessage()));
        }
    }

    @GetMapping("code")
    public ResponseEntity<Object> getCurrentWeatherDataByCode(@RequestParam String code,
                                                              @RequestParam(required = false) String appid,
                                                              @RequestParam(required = false) String units,
                                                              @RequestParam(required = false) String lang)
    {
        var queryWithCode = new WeatherDataQueryWithCode(appid, units, lang, code);

        try {
            var data = m_currentWeatherService.getCurrentWeatherDataByCode(queryWithCode);

            return ResponseEntity.ok(data);
        }
        catch (RuntimeException ex) {
            return ResponseEntity.ok(new NoWeatherDataDto(ex.getMessage()));
        }
    }
}
