package com.clearsoft.welivre.domain.api.request;

/**
 * Created by on 03.07.17.
 */

public class LoginRequest {

    String email;
    String password;

    public LoginRequest() {
    }

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
