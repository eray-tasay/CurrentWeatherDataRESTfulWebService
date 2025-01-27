package com.eraytasay.weather.service.currentdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.eraytasay")
public class CurrentWeatherDataWebServiceApplication {
	public static void main(String[] args)
	{
		SpringApplication.run(CurrentWeatherDataWebServiceApplication.class, args);
	}
}
