package com.clearsoft.welivre.domain.api.response;

import com.clearsoft.welivre.domain.api.dto.FollowDto;

import java.util.List;

/**
 * Created by on 01.08.17.
 */

public class FollowResponse {

    int status;
    List<FollowDto> result;

    public FollowResponse() {
    }

    public FollowResponse(int status, List<FollowDto> result) {
        this.status = status;
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public List<FollowDto> getResult() {
        return result;
    }
}
