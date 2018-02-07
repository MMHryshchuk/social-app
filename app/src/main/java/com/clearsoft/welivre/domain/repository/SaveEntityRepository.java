package com.clearsoft.welivre.domain.repository;


import com.clearsoft.welivre.domain.api.dto.FeedDto;
import com.clearsoft.welivre.domain.api.dto.PlanDto;
import com.clearsoft.welivre.domain.api.response.FeedResponse;
import com.clearsoft.welivre.domain.api.response.FollowResponse;
import com.clearsoft.welivre.domain.entities.Feed;

/**
 * Created by vladimir on 08.06.16.
 */
public interface SaveEntityRepository {

    String FEED_ALL = "FEED_ALL";
    String FEED_FOLLOWING = "FEED_FOLLOWING";
    String FEED_SOS = "FEED_SOS";
    String FEED_USER = "FEED_USER";

    void saveFeedAndRelatedData(FeedResponse feedDto, String type);

    void updateFeed(Feed feed);

    void savePlan(PlanDto planDto);

    void saveFollow(FollowResponse response);
}
