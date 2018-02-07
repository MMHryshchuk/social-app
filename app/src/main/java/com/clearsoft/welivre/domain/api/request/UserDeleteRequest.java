package com.clearsoft.welivre.domain.api.request;

/**
 * Created by on 27.07.17.
 */

public class UserDeleteRequest {

    int user_id;

    public UserDeleteRequest() {
    }

    public UserDeleteRequest(int user_id) {
        this.user_id = user_id;
    }
}
