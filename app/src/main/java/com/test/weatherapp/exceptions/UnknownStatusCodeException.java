package com.test.weatherapp.exceptions;

/**
 * Created by alex-balandin on 8/9/18
 */
public class UnknownStatusCodeException extends RuntimeException {

    public UnknownStatusCodeException(int statusCode) {
        super("unknown status code: " + String.valueOf(statusCode));
    }
}
