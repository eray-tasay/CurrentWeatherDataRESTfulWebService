This project is a Web service that provides current weather data. To do that, OpenWeather's current weather data service is consumed (https://openweathermap.org/current). 
This Web service has some features that OpenWeather's service does not have. First, in addition to accepting longitude and latitude, it also accepts place names. 
To find out the location of the place, it first consumes OpenWeather's Geocoding API (https://openweathermap.org/api/geocoding-api). Even though there is a built-in 
Geocoding feature in their service, it is deprecated. Secondly, OpenWeather's service requires users to input an API key; this Web service does not. 
In this case it uses the default API key. Lastly, this Web service logs the requests in terms of their API keys.
