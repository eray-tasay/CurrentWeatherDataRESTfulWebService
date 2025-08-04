This project is a RESTful web service that provides current weather data. To do that, OpenWeather's current weather data service is consumed (https://openweathermap.org/current). 
This RESTful web service has some features that OpenWeather's service does not have. First, in addition to accepting longitude and latitude, it also accepts place names. 
To find out the location of the place, it first consumes OpenWeather's Geocoding API (https://openweathermap.org/api/geocoding-api). Even though there is a built-in 
Geocoding feature in their service, it is deprecated. Secondly, OpenWeather's service requires users to input an API key; this Web service does not. 
In this case it uses the default API key. Lastly, this Web service logs the requests in terms of their API keys. Web service endpoints are listed below:

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
Now I am going to explain the database tables. First, I give the schema of the database:

<img width="502" alt="databaseSchema" src="https://github.com/user-attachments/assets/f72a14a4-b483-435b-a7af-86a9db798454" />

The api_key_queries table shows how many times an API key is used to request OpenWeather's services (both the current weather data service and geocoding service). It is given with some test data in it below:
```
API_KEY_QUERY_ID  	API_KEY  	                        COUNT  
1	                da8af116d20ecb15078a54947b942bd3	7
2	                691d19d137643d975b9c9ce57529574f	3
```
The current_weather_data_api_queries table shows how many times and when API keys were used to request the OpenWeather current weather data service. This table is given below:
```
CURRENT_WEATHER_DATA_API_QUERY_ID   COUNT   QUERY_DATETIME                API_KEY_QUERY_ID  
1                                   1       2025-02-19 08:02:01.90053     1
2                                   3       2025-02-19 08:02:23.382043    1
3                                   5       2025-02-19 08:02:29.965124    1
4                                   1       2025-02-19 08:03:32.343472    2
5                                   7       2025-02-19 08:03:40.075724    1
6                                   3       2025-02-19 08:03:57.283828    2
```
The geocoding_queries table shows how many times and when API keys were used to request the OpenWeather Geocoding API. This table is given below:
```
GEOCODING_QUERY_ID      COUNT   QUERY_DATETIME                 API_KEY_QUERY_ID  
1                       2       2025-02-19 08:02:23.256917     1
2                       4       2025-02-19 08:02:29.844741     1
3                       6       2025-02-19 08:03:39.974133     1
4                       2       2025-02-19 08:03:57.187405     2
```
