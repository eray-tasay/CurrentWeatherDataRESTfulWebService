package com.eraytasay.service.weather.current.repository;

import com.eraytasay.service.weather.current.entity.KeyQuery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IKeyQueryRepository extends CrudRepository<KeyQuery, Integer> {
}
