package com.clearsoft.welivre.domain.repository.impl;

import com.clearsoft.welivre.domain.entities.DaoSession;
import com.clearsoft.welivre.domain.entities.Plan;
import com.clearsoft.welivre.domain.repository.PlanRepository;

import io.reactivex.Observable;

/**
 * Created by on 20.07.17.
 */

public class PlanRepositoryImpl implements PlanRepository {

    private DaoSession daoSession;

    public PlanRepositoryImpl(DaoSession daoSession) {
        this.daoSession = daoSession;
    }

    @Override
    public Observable<Plan> getMyPlan() {
        return Observable.create(e -> {
            Plan plan = daoSession.getPlanDao()
                    .queryBuilder()
                    .unique();
            if (plan == null){
                plan = new Plan("","","0000-00-00","","","","","","","","","");
            }
            e.onNext(plan);
            e.onComplete();
        });
    }
}
