package com.clearsoft.welivre.domain.api.response;

import com.clearsoft.welivre.domain.api.dto.PlanDto;

/**
 * Created by on 20.07.17.
 */

public class PlanDtoResponse {

    String status;
    PlanDto result;

    public PlanDtoResponse() {
    }

    public String getStatus() {
        return status;
    }

    public PlanDto getResult() {
        return result;
    }
}
