package com.eraytasay.service.weather.current.exp;

public class NoSuchPlaceException extends RuntimeException {
    public NoSuchPlaceException(String message)
    {
        super(message);
    }

    public NoSuchPlaceException()
    {
    }
}
