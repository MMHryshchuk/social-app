package com.clearsoft.welivre.domain.api.request;

/**
 * Created by on 11.08.17.
 */

public class AboutLikeRequest {

    int user_id_about_owner;
    int user_id_who_liked;

    public AboutLikeRequest() {
    }

    public AboutLikeRequest(int user_id_about_owner, int user_id_who_liked) {
        this.user_id_about_owner = user_id_about_owner;
        this.user_id_who_liked = user_id_who_liked;
    }
}
