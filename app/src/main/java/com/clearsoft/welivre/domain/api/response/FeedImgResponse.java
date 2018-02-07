package com.clearsoft.welivre.domain.api.response;

import com.clearsoft.welivre.domain.api.dto.FeedDto;

/**
 * Created by on 28.07.17.
 */

public class FeedImgResponse {

    String status;
    FeedDto result;

    public FeedImgResponse() {
    }

    public FeedImgResponse(String status, FeedDto result) {
        this.status = status;
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public FeedDto getResult() {
        return result;
    }
}
