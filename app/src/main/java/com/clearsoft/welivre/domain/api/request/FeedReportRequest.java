package com.clearsoft.welivre.domain.api.request;

/**
 * Created by on 12.07.17.
 */

public class FeedReportRequest {

    String user_id;
    String post_id_denouncer;
    String motive;

    public FeedReportRequest() {
    }

    public FeedReportRequest(String user_id, String post_id_denouncer, String motive) {
        this.user_id = user_id;
        this.post_id_denouncer = post_id_denouncer;
        this.motive = motive;
    }
}
