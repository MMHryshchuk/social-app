package com.clearsoft.welivre.domain.repository.impl;

import com.clearsoft.welivre.domain.entities.DaoSession;
import com.clearsoft.welivre.domain.entities.Follow;
import com.clearsoft.welivre.domain.repository.FollowRepository;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by on 02.08.17.
 */

public class FollowRepositoryImpl implements FollowRepository {

    private DaoSession daoSession;

    public FollowRepositoryImpl(DaoSession daoSession) {
        this.daoSession = daoSession;
    }


    @Override
    public Observable<List<Follow>> getAllFollowing() {
        return Observable.create(e -> {
        List<Follow> followList = daoSession.getFollowDao()
                .queryBuilder()
                .list();
            e.onNext(followList);
            e.onComplete();
        });
    }
}
