package com.eraytasay.service.weather.current.repository;

import com.eraytasay.service.weather.current.entity.GeocodingQuery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGeocodingQueryRepository extends CrudRepository<GeocodingQuery, Integer> {
}
