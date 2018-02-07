package com.clearsoft.welivre.domain.api.response;

import com.clearsoft.welivre.domain.api.dto.SpecUserInfoDto;

/**
 * Created by on 17.08.17.
 */

public class ProfileResponse {

    int status;
    SpecUserInfoDto result;

    public ProfileResponse() {
    }

    public ProfileResponse(int status, SpecUserInfoDto result) {
        this.status = status;
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public SpecUserInfoDto getResult() {
        return result;
    }
}
