package com.eraytasay.service.weather.current.repository;

import com.eraytasay.service.weather.current.entity.ApiKeyQuery;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IApiKeyQueryRepository extends CrudRepository<ApiKeyQuery, Integer> {
    @Query("update ApiKeyQuery akq set akq.count = akq.count + 1 where akq.apiKey = :apiKey")
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    void incrementCountByApiKey(@Param("apiKey") String apiKey);

    boolean existsByApiKey(String apiKey);
    Optional<ApiKeyQuery> findByApiKey(String apiKey);
}
