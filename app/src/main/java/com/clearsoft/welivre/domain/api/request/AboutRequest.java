package com.clearsoft.welivre.domain.api.request;

/**
 * Created by on 10.08.17.
 */

public class AboutRequest {

    int user_id;
    int target_id;

    public AboutRequest() {
    }

    public AboutRequest(int user_id, int target_id) {
        this.user_id = user_id;
        this.target_id = target_id;
    }


}
