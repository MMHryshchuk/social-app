package com.clearsoft.welivre.domain.api.response;

import com.clearsoft.welivre.domain.api.dto.FeedDto;

import java.util.List;

/**
 * Created by on 13.07.17.
 */

public class FeedResponse {

    int status;
    List<FeedDto> result;

    public FeedResponse() {
    }

    public int getStatus() {
        return status;
    }

    public List<FeedDto> getResult() {
        return result;
    }
}
