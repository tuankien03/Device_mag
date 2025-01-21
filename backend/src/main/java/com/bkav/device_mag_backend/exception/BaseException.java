package com.bkav.device_mag_backend.exception;

public class BaseException extends RuntimeException {
    int status;

    public BaseException(String message) {
        super(message);
    }
}
