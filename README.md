This project is a Web service that provides current weather data. To do that, OpenWeather's current weather data service is consumed (https://openweathermap.org/current). 
This Web service has some features that OpenWeather's service does not have. First, in addition to accepting longitude and latitude, it also accepts place names. 
To find out the location of the place, it first consumes OpenWeather's Geocoding API (https://openweathermap.org/api/geocoding-api). Even though there is a built-in 
Geocoding feature in their service, it is deprecated. Secondly, OpenWeather's service requires users to input an API key; this Web service does not. 
In this case it uses the default API key. Lastly, this Web service logs the requests in terms of their API keys. Please note that this project is not a project that
competes with OpenWeather. It is just a learning project. Web service end points are listed below. Please consult the OpenWeather documentations for additional information about request parameters.

```
/api/weather/current/coord?lat={latitude}&lon={longitude}[&appid={API key}][&units={units}][&lang={language}]
/api/weather/current/name?name={placeName}[&appid={API key}][&units={units}][&lang={language}]
/api/weather/current/code?code={code}[&appid={API key}][&units={units}][&lang={language}]
```

Some example requests and their outputs are given below:

```
/api/weather/current/coord?lat=41&lon=28

{
    "coord": {
        "lon": 28.0,
        "lat": 41.0
    },
    "weather": [
        {
            "id": 803,
            "main": "Clouds",
            "description": "broken clouds",
            "icon": "04d"
        }
    ],
    "base": "stations",
    "main": {
        "temp": 279.79,
        "feels_like": 275.88,
        "temp_min": 279.79,
        "temp_max": 280.23,
        "pressure": 1022,
        "humidity": 70,
        "sea_level": 1022,
        "grnd_level": 1017
    },
    "visibility": 10000,
    "wind": {
        "speed": 6.69,
        "deg": 50,
        "gust": 0.0
    },
    "clouds": {
        "all": 75
    },
    "dt": 1739888422,
    "sys": {
        "type": 2,
        "id": 2001204,
        "country": "TR",
        "sunrise": 1739854717,
        "sunset": 1739893532
    },
    "timezone": 10800,
    "id": 741725,
    "name": "Erekli",
    "cod": 200
}
```
```
/api/weather/current/name?name=İstanbul

{
    "coord": {
        "lon": 28.9662,
        "lat": 41.0092
    },
    "weather": [
        {
            "id": 803,
            "main": "Clouds",
            "description": "broken clouds",
            "icon": "04d"
        }
    ],
    "base": "stations",
    "main": {
        "temp": 280.11,
        "feels_like": 275.66,
        "temp_min": 280.11,
        "temp_max": 281.0,
        "pressure": 1021,
        "humidity": 61,
        "sea_level": 1021,
        "grnd_level": 1014
    },
    "visibility": 10000,
    "wind": {
        "speed": 8.75,
        "deg": 70,
        "gust": 0.0
    },
    "clouds": {
        "all": 75
    },
    "dt": 1739888558,
    "sys": {
        "type": 2,
        "id": 2092591,
        "country": "TR",
        "sunrise": 1739854486,
        "sunset": 1739893300
    },
    "timezone": 10800,
    "id": 738354,
    "name": "Karaköy",
    "cod": 200
}
```
```
/api/weather/current/code?code=34720,TR

{
    "coord": {
        "lon": 29.0658,
        "lat": 40.9669
    },
    "weather": [
        {
            "id": 804,
            "main": "Clouds",
            "description": "overcast clouds",
            "icon": "04d"
        }
    ],
    "base": "stations",
    "main": {
        "temp": 279.91,
        "feels_like": 276.09,
        "temp_min": 279.79,
        "temp_max": 281.23,
        "pressure": 1021,
        "humidity": 61,
        "sea_level": 1021,
        "grnd_level": 1016
    },
    "visibility": 10000,
    "wind": {
        "speed": 6.52,
        "deg": 64,
        "gust": 8.39
    },
    "clouds": {
        "all": 100
    },
    "dt": 1739888986,
    "sys": {
        "type": 2,
        "id": 2092591,
        "country": "TR",
        "sunrise": 1739854459,
        "sunset": 1739893279
    },
    "timezone": 10800,
    "id": 6947637,
    "name": "Ataşehir",
    "cod": 200
}
```

