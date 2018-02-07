package com.clearsoft.welivre.domain.api.response;

import com.clearsoft.welivre.domain.api.dto.UpdateImageDto;

/**
 * Created by on 28.08.17.
 */

public class UpdateImageResponse {

    int status;
    UpdateImageDto result;

    public UpdateImageResponse(int status) {
        this.status = status;
    }

    public UpdateImageResponse(int status, UpdateImageDto result) {
        this.status = status;
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public UpdateImageDto getResult() {
        return result;
    }
}
