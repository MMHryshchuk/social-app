package com.clearsoft.welivre.domain.api.request;

/**
 * Created by on 18.07.17.
 */

public class SmokingRequest {

    int user_id;
    String situation;

    public SmokingRequest() {
    }

    public SmokingRequest(int user_id, String situation) {
        this.user_id = user_id;
        this.situation = situation;
    }
}
