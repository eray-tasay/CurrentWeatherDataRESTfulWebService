package com.eraytasay.weather.service.currentdata.lib.service.exp;

public class NoSuchPlaceException extends RuntimeException {
    public NoSuchPlaceException(String message)
    {
        super(message);
    }

    public NoSuchPlaceException()
    {
    }
}
