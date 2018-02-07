package com.clearsoft.welivre.domain.api.request;

/**
 * Created by on 03.07.17.
 */

public class RegisterRequest {

    String email;
    String name;
    String password;
    String usertype;
    String device;
    String token;
    String language;

    public RegisterRequest() {
    }

    public RegisterRequest(String email,String name, String password, String usertype, String device, String token, String language) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.usertype = usertype;
        this.device = device;
        this.token = token;
        this.language = language;
    }
}
