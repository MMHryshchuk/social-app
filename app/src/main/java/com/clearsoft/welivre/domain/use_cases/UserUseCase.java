package com.clearsoft.welivre.domain.use_cases;

import com.annimon.stream.Stream;
import com.clearsoft.welivre.ui.dvo.AboutDvo;
import com.clearsoft.welivre.ui.dvo.CommentDvo;
import com.clearsoft.welivre.ui.dvo.CommentsListDvo;
import com.clearsoft.welivre.ui.dvo.FollowingListDvo;
import com.clearsoft.welivre.ui.dvo.ProfileDvo;
import com.clearsoft.welivre.ui.dvo.QuestionariesDvo;
import com.clearsoft.welivre.ui.dvo.SettingsProfileDvo;

import io.reactivex.Observable;

/**
 * Created by on 18.07.17.
 */

public interface UserUseCase {

    Observable<Object> setUserSituation(int situation, String cigaDayliNum, String cigaPackCost, String cigaWakeUpMinutes, String cigaStartAge, String birthday, String gender);

    Observable<Object> setSmokingStatus(String situation);

    Observable<Object> deleteAccaunt();

    Observable<FollowingListDvo> getAllFollowers(String userId, long page);

    Observable<FollowingListDvo> getAllFollowings(String userId, long page);

    Observable<Object> follow(String followId);

    Observable<Object> unFollow(String unFollowId);

    Observable<AboutDvo> getUserAbout(int targetId);

    Observable<String> likeAbout(int targetId);

    Observable<String> unLikeAbout(int targetId);

    Observable<String> createUpdateAbout(String  about);

    Observable<CommentsListDvo> getAboutComments(int userId,long lastIndex);

    Observable<Object> aboutPostComment(int targetId, String about);

    Observable<SettingsProfileDvo> getUserProfileInfo();

    Observable<SettingsProfileDvo> updateUser(String name, String email, String password, String about,String path);

    Observable<QuestionariesDvo> getUserQuestionaries();

    Observable<ProfileDvo> getSpecificUserInfo(int targetId);

    Observable<String> uploadAvatar(String path);
}
