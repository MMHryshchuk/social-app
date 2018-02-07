package com.clearsoft.welivre.domain.api;

import com.clearsoft.welivre.domain.api.request.AboutCommentRequest;
import com.clearsoft.welivre.domain.api.request.AboutCrateUpdateRequest;
import com.clearsoft.welivre.domain.api.request.AboutCreateCommentRequest;
import com.clearsoft.welivre.domain.api.request.AboutLikeRequest;
import com.clearsoft.welivre.domain.api.request.AboutRequest;
import com.clearsoft.welivre.domain.api.request.SpecificUserRequest;
import com.clearsoft.welivre.domain.api.response.AboutResponse;
import com.clearsoft.welivre.domain.api.request.FollowersRequest;
import com.clearsoft.welivre.domain.api.request.FollowingRequest;
import com.clearsoft.welivre.domain.api.request.SmokingRequest;
import com.clearsoft.welivre.domain.api.request.UnFollowingRequest;
import com.clearsoft.welivre.domain.api.request.UserDeleteRequest;
import com.clearsoft.welivre.domain.api.request.UserSituationRequest;
import com.clearsoft.welivre.domain.api.response.AuthDtoResponse;
import com.clearsoft.welivre.domain.api.response.CommentAboutDtoResponse;
import com.clearsoft.welivre.domain.api.response.CommentDtoResponse;
import com.clearsoft.welivre.domain.api.response.FeedBackResponse;
import com.clearsoft.welivre.domain.api.response.FollowResponse;
import com.clearsoft.welivre.domain.api.response.ProfileResponse;
import com.clearsoft.welivre.domain.api.response.UpdateImageResponse;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * Created by on 18.07.17.
 */

public interface UserApi {

    @POST("mobile/usermanager/setUserSituation")
    Observable<Object> setUserSituation(@Body UserSituationRequest request);

    @POST("mobile/usermanager/setCurrentSmokingStatus")
    Observable<Object> setSmokingStatus(@Body SmokingRequest request);

    @POST("mobile/usermanager/deleteAccount")
    Observable<Object> deleteUserAccoutn(@Body UserDeleteRequest request);

    @POST("mobile/usermanager/getFollowers")
    Observable<FollowResponse> getAllFollowers(@Body FollowersRequest request);

    @POST("mobile/usermanager/getFollowings")
    Observable<FollowResponse> getAllFollowing(@Body FollowersRequest request);


    @POST("mobile/usermanager/follow")
    Observable<Object> followUser(@Body FollowingRequest request);

    @POST("mobile/usermanager/unfollow")
    Observable<Object> unFollowUser(@Body UnFollowingRequest request);

    @POST("mobile/usermanager/getAbout")
    Observable<AboutResponse> getUserAbout(@Body AboutRequest request);

    @POST("mobile/usermanager/aboutLike")
    Observable<FeedBackResponse> likeUserAbout(@Body AboutLikeRequest request);

    @POST("mobile/usermanager/aboutUnLike")
    Observable<FeedBackResponse> unLikeUserAbout(@Body AboutLikeRequest request);

    @POST("mobile/usermanager/about")
    Observable<FeedBackResponse> createUpdateAbout(@Body AboutCrateUpdateRequest request);

    @POST("mobile/usermanager/aboutComments")
    Observable<FeedBackResponse> createAboutComment(@Body AboutCreateCommentRequest request);

    @POST("mobile/usermanager/getAboutComments")
    Observable<CommentAboutDtoResponse> getAboutComments(@Body AboutCommentRequest request);

    @Multipart
    @POST("mobile/usermanager/updateUser")
    Observable<AuthDtoResponse> updateUserInfo(@PartMap Map<String, RequestBody> params, @Part MultipartBody.Part image);

    @POST("mobile/usermanager/getSpecificUserInfo")
    Observable<ProfileResponse> getSpecificUserInfo(@Body SpecificUserRequest request);

    @Multipart
    @POST("mobile/usermanager/uploadAvatar")
    Observable<UpdateImageResponse> updateProfileImage(@PartMap Map<String, RequestBody> params, @Part MultipartBody.Part image);

}
