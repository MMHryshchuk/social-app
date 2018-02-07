package com.clearsoft.welivre.domain.api.request;

/**
 * Created by on 18.07.17.
 */

public class AssistancesRequest {

    int plan_id;
    String  name;

    public AssistancesRequest() {
    }

    public AssistancesRequest(int plan_id, String name) {
        this.plan_id = plan_id;
        this.name = name;
    }
}
