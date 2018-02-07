package com.clearsoft.welivre.domain.api.request;

/**
 * Created by on 18.07.17.
 */

public class CravingsRequest {

    int plan_id;
    String text;

    public CravingsRequest(int plan_id) {
        this.plan_id = plan_id;
    }

    public CravingsRequest(int plan_id, String text) {
        this.plan_id = plan_id;
        this.text = text;
    }
}
