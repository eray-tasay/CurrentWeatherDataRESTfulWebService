package com.eraytasay.service.weather.current.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "geocoding_queries")
public class GeocodingQuery {
    @Id
    @Column(name = "geocoding_query_id")
    public int id;

    @Column(nullable = false)
    public int count;

    @Column(name = "query_datetime", nullable = false)
    public LocalDateTime queryDatetime = LocalDateTime.now();

    @JoinColumn(name = "key_query_id")
    @ManyToOne
    public KeyQuery key;
}
