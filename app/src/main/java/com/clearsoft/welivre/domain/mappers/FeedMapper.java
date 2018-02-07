package com.clearsoft.welivre.domain.mappers;

import com.annimon.stream.Stream;
import com.clearsoft.welivre.core.utils.StringUtils;
import com.clearsoft.welivre.domain.api.dto.FeedDto;
import com.clearsoft.welivre.domain.api.response.FeedResponse;
import com.clearsoft.welivre.domain.entities.Feed;
import com.clearsoft.welivre.ui.dvo.FeedDvo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by on 13.07.17.
 */

public class FeedMapper {

    public FeedMapper() {
    }

    public List<Feed> mapFeed(FeedResponse feedResponse,String type){
        List<Feed> feeds = new ArrayList<>();
        Stream.of(feedResponse.getResult()).forEach(feedDto -> {
            Feed feed = new Feed();
            feed.setPostId(feedDto.getPostId());
            feed.setFeedType(type);
            feed.setPostContentTxt(feedDto.getPost_contentTxt());
            feed.setPostContentImg(StringUtils.isNullEmpty(feedDto.getPost_contentImg()) ? "" :"http://welivreapp.com/welivre_ci/upload/posts/" + feedDto.getPost_contentImg() );
            feed.setPostSos(feedDto.getPostSos());
            feed.setPostTimestamp(feedDto.getPostTimestamp());
            feed.setPostFavorites(feedDto.getPostFavorites());
            feed.setPostComments(feedDto.getPostComments());
            feed.setPostLikes(feedDto.getPostLikes());
            feed.setPostShares(feedDto.getPostShares());
            feed.setPostLiked(feedDto.getPostLiked());
            feed.setPostFavorited(feedDto.getPostFavorited());
            feed.setUserName(feedDto.getUserName());
            feed.setUserAvatar(StringUtils.isNullEmpty(feedDto.getUserAvatar()) ? "" : "http://welivreapp.com/welivre_ci/upload/profile/" + feedDto.getUserAvatar());
            feed.setUserId(feedDto.getUserId());
            feed.setUserFollowers(feedDto.getUserFollowers());
            feed.setUserFollowings(feedDto.getUserFollowings());
            feed.setUserPosts(feedDto.getUserPosts());
            feeds.add(feed);
        });

        return feeds;
    }

    public FeedDvo mapFeedDvo(Feed feed){
        FeedDvo feedDvo = new FeedDvo();
        feedDvo.setPostId(feed.getPostId());
        feedDvo.setPostContentTxt(feed.getPostContentTxt());
        feedDvo.setPostContentImg(feed.getPostContentImg());
        feedDvo.setPostSos(feed.getPostSos());
        feedDvo.setPostTimestamp(feed.getPostTimestamp());
        feedDvo.setPostFavorites(feed.getPostFavorites());
        feedDvo.setPostComments(feed.getPostComments());
        feedDvo.setPostLikes(feed.getPostLikes());
        feedDvo.setPostShares(feed.getPostShares());
        feedDvo.setPostLiked(feed.getPostLiked());
        feedDvo.setPostFavorited(feed.getPostFavorited());
        feedDvo.setUserName(feed.getUserName());
        feedDvo.setUserAvatar(feed.getUserAvatar());
        feedDvo.setUserId(feed.getUserId());
        feedDvo.setUserFollowers(feed.getUserFollowers());
        feedDvo.setUserFollowings(feed.getUserFollowings());
        feedDvo.setUserPosts(feed.getUserPosts());
        return feedDvo;
    }
}
