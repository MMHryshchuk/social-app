package com.clearsoft.welivre.domain.api.request;

/**
 * Created by on 03.07.17.
 */

public class ResetRequest {

    String email;
    String language;

    public ResetRequest() {
    }

    public ResetRequest(String email, String language) {
        this.email = email;
        this.language = language;
    }


}
