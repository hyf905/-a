package com.ateam05.hotel.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Author: Huyufan
 * @Description:
 */
public class NotLoginException extends AuthenticationException {

    public NotLoginException(String msg, Throwable t) {
        super(msg, t);
    }

    public NotLoginException(String msg) {
        super(msg);
    }
}
