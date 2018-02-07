package com.clearsoft.welivre.domain.repository;

import com.clearsoft.welivre.domain.entities.Follow;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by on 02.08.17.
 */

public interface FollowRepository {

    Observable<List<Follow>> getAllFollowing();
}
