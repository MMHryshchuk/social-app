package com.clearsoft.welivre.domain.mappers;

import com.clearsoft.welivre.domain.api.dto.PlanDto;
import com.clearsoft.welivre.domain.api.response.PlanDtoResponse;
import com.clearsoft.welivre.domain.entities.Plan;

/**
 * Created by on 20.07.17.
 */

public class PlanMapper {

    public PlanMapper() {
    }

    public Plan mapPlan(PlanDto dto){
        Plan plan = new Plan();
        plan.setId(String.valueOf(dto.getId()));
        plan.setUserId(dto.getUserId());
        plan.setQuitDate(dto.getQuitDate());
        plan.setMotivationsIds(dto.getMotivationsIds());
        plan.setTriggersIds(dto.getTriggersIds());
        plan.setCravingsIds(dto.getCravingsIds());
        plan.setAssistancesIds(dto.getAssistancesIds());
        plan.setPMotivationPhoto(dto.getP_motivationPhoto());
        plan.setPMotivationVideo(dto.getP_motivationVideo());
        plan.setPMotivationAudio(dto.getP_motivationAudio());
        plan.setRelapsed(dto.getRelapsed());
        plan.setTimestamp(dto.getTimestamp());
        return plan;
    }
}
