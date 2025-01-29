package com.eraytasay.service.weather.current;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.eraytasay")
@EnableJpaRepositories(basePackages = "com.eraytasay")
@EntityScan(basePackages = "com.eraytasay")
public class CurrentWeatherDataWebServiceApplication {
	public static void main(String[] args)
	{
		SpringApplication.run(CurrentWeatherDataWebServiceApplication.class, args);
	}
}
