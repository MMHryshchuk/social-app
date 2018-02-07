package com.clearsoft.welivre.domain.repository;

import com.clearsoft.welivre.domain.entities.Plan;

import io.reactivex.Observable;

/**
 * Created by on 20.07.17.
 */

public interface PlanRepository {
    Observable<Plan> getMyPlan();
}
