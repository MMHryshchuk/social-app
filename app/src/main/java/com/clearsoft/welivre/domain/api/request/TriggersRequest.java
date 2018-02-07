package com.clearsoft.welivre.domain.api.request;

/**
 * Created by on 18.07.17.
 */

public class TriggersRequest {

    int plan_id;
    String name;
    String tab;

    public TriggersRequest() {
    }

    public TriggersRequest(int plan_id, String name, String tab) {
        this.plan_id = plan_id;
        this.name = name;
        this.tab = tab;
    }
}
