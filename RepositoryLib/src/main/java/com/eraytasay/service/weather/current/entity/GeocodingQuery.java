package com.eraytasay.service.weather.current.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "geocoding_queries")
public class GeocodingQuery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "geocoding_query_id")
    public int id;

    @Column(nullable = false)
    public int count;

    @Column(name = "query_datetime", nullable = false)
    public LocalDateTime queryDatetime = LocalDateTime.now();

    @JoinColumn(name = "api_key_query_id")
    @ManyToOne
    public ApiKeyQuery apiKeyQuery;
}
