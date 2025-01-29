package com.eraytasay.service.weather.current.repository;

import com.eraytasay.service.weather.current.entity.CurrentWeatherDataApiQuery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICurrentWeatherDataApiQueryRepository extends CrudRepository<CurrentWeatherDataApiQuery, Integer> {
}
