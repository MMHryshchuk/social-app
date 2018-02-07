package com.clearsoft.welivre.domain.api.request;

/**
 * Created by on 11.08.17.
 */

public class AboutCrateUpdateRequest {

    int user_id;
    String about;

    public AboutCrateUpdateRequest() {
    }

    public AboutCrateUpdateRequest(int user_id, String about) {
        this.user_id = user_id;
        this.about = about;
    }
}
