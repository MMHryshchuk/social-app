package com.clearsoft.welivre.domain.api.request;

/**
 * Created by on 17.08.17.
 */

public class SpecificUserRequest {

    int user_id;
    int target_id;

    public SpecificUserRequest() {
    }

    public SpecificUserRequest(int user_id, int target_id) {
        this.user_id = user_id;
        this.target_id = target_id;
    }
}
