package com.clearsoft.welivre.domain.exceptions;

/**
 * Created by on 10.10.16.
 */

public class AuthException extends Exception {

    public AuthException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
