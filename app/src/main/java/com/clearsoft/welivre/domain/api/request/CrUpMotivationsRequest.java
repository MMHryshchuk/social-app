package com.clearsoft.welivre.domain.api.request;

/**
 * Created by on 18.07.17.
 */

public class CrUpMotivationsRequest {

    int plan_id;
    String name;
    String image;

    public CrUpMotivationsRequest() {
    }

    public CrUpMotivationsRequest(int plan_id, String name, String image) {
        this.plan_id = plan_id;
        this.name = name;
        this.image = image;
    }
}
