package com.clearsoft.welivre.domain.api.response;

import com.clearsoft.welivre.domain.api.dto.UserDto;

/**
 * Created by on 03.07.17.
 */

public class AuthDtoResponse {
    int status;
    UserDto result;
    String message;

    public AuthDtoResponse() {
    }

    public AuthDtoResponse(int status, UserDto result) {
        this.status = status;
        this.result = result;
    }

    public AuthDtoResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public UserDto getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }
}
