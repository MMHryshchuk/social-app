package com.clearsoft.welivre.domain.api.request;

/**
 * Created by on 13.07.17.
 */

public class NewPostRequest {

    int user_id;
    String content_txt;
    int sos;
    String language;

    public NewPostRequest() {
    }

    public NewPostRequest(int user_id, String content_txt, int sos, String language) {
        this.user_id = user_id;
        this.content_txt = content_txt;
        this.sos = sos;
        this.language = language;
    }
}
