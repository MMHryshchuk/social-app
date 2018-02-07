package com.clearsoft.welivre.domain.api.response;

import com.clearsoft.welivre.domain.api.dto.AboutDto;

import java.util.List;

/**
 * Created by on 10.08.17.
 */

public class AboutResponse {
    int status;
    List<AboutDto> result;

    public AboutResponse() {
    }

    public AboutResponse(int status, List<AboutDto> result) {
        this.status = status;
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public List<AboutDto> getResult() {
        return result;
    }
}
