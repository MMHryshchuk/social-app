package com.clearsoft.welivre.domain.api.response;

import com.clearsoft.welivre.domain.api.dto.FeedDto;

import java.util.List;

/**
 * Created by on 13.07.17.
 */

public class PostResponse {

    int status;
    FeedDto result;

    public PostResponse() {
    }

    public PostResponse(int status, FeedDto result) {
        this.status = status;
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public FeedDto getResult() {
        return result;
    }
}
