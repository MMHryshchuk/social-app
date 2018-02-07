package com.clearsoft.welivre.domain.repository;

import com.clearsoft.welivre.domain.entities.Feed;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by on 13.07.17.
 */

public interface FeedRepository {

    Observable<List<Feed>> getAllFeeds(String hideIds);
    Observable<List<Feed>> getFollowingFeeds(String hideIds);
    Observable<List<Feed>> getSosFeeds(String hideIds);
    Observable<List<Feed>> getUserFeeds(String userId);

    Observable<Feed> getFeed(String postId);

    Observable<List<Feed>> getAllFavorites();
}
