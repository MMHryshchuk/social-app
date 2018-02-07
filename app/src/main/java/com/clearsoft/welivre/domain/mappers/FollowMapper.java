package com.clearsoft.welivre.domain.mappers;

import com.annimon.stream.Stream;
import com.clearsoft.welivre.domain.api.response.FollowResponse;
import com.clearsoft.welivre.domain.entities.Follow;
import com.clearsoft.welivre.ui.dvo.FollowingDvo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by on 02.08.17.
 */

public class FollowMapper {

    public List<Follow> mapFollow (FollowResponse response){
        List<Follow> followList = new ArrayList<>();
        Stream.of(response.getResult()).forEach(dto -> {
            Follow follow = new Follow();
            follow.setFollowId(dto.getFollowId());
            follow.setFollowTimestamp(dto.getFollowTimestamp());
            follow.setUserId(dto.getUserId());
            follow.setUserName(dto.getUserName());
            follow.setUserEmail(dto.getUserEmail());
            follow.setUserLanguage(dto.getUserLanguage());
            follow.setUserFollowings(dto.getUserFollowings());
            follow.setUserFollowers(dto.getUserFollowers());
            follow.setUserAvatar(dto.getUserAvatar());
            follow.setUserPosts(dto.getUserPosts());
            follow.setUserFollowed(dto.getUserFollowed());
            followList.add(follow);
        });

        return followList;
    }

    public List<FollowingDvo> mapFollowDvo(FollowResponse response){
        List<FollowingDvo> followingDvos = new ArrayList<>();
        Stream.of(response.getResult()).forEach(dto -> {
            FollowingDvo followingDvo = new FollowingDvo();
            followingDvo.setFollowId(dto.getFollowId());
            followingDvo.setUserId(dto.getUserId());
            followingDvo.setUserImage("http://welivreapp.com/welivre_ci/upload/profile/"+dto.getUserAvatar());
            followingDvo.setUserName(dto.getUserName());
            followingDvo.setUserEmail(dto.getUserEmail());
            followingDvo.setUserFollowers(dto.getUserFollowers());
            followingDvo.setUserFollowings(dto.getUserFollowings());
            followingDvo.setUserFollowed(dto.getUserFollowed());
            followingDvos.add(followingDvo);
        });
        return followingDvos;
    }
}
