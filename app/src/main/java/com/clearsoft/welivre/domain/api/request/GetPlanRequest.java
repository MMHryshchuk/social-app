package com.clearsoft.welivre.domain.api.request;

/**
 * Created by on 18.07.17.
 */

public class GetPlanRequest {

    int user_id;

    public GetPlanRequest() {
    }

    public GetPlanRequest(int user_id) {
        this.user_id = user_id;
    }
}
