package com.clearsoft.welivre.domain.api;

import com.clearsoft.welivre.domain.api.dto.CommentDto;
import com.clearsoft.welivre.domain.api.request.CommentDeleteRequest;
import com.clearsoft.welivre.domain.api.request.CommentRequest;
import com.clearsoft.welivre.domain.api.response.CommentDtoResponse;
import com.clearsoft.welivre.domain.api.response.FeedBackResponse;
import com.clearsoft.welivre.domain.api.request.FeedActionRequest;
import com.clearsoft.welivre.domain.api.request.FeedAllRequest;
import com.clearsoft.welivre.domain.api.request.FeedCreateUpdateRequest;
import com.clearsoft.welivre.domain.api.request.FeedFollowingRequest;
import com.clearsoft.welivre.domain.api.request.FeedReportRequest;
import com.clearsoft.welivre.domain.api.request.FeedShareRequest;
import com.clearsoft.welivre.domain.api.request.FeedSosRequest;
import com.clearsoft.welivre.domain.api.request.FeedUserRequest;
import com.clearsoft.welivre.domain.api.request.NewPostRequest;
import com.clearsoft.welivre.domain.api.response.FeedImgResponse;
import com.clearsoft.welivre.domain.api.response.FeedResponse;
import com.clearsoft.welivre.domain.api.response.PostResponse;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * Created by on 04.07.17.
 */

public interface FeedApi {
    @Multipart
    @POST("mobile/postmanager/post")
    Observable<PostResponse> newPost(@PartMap Map<String, RequestBody> params, @Part MultipartBody.Part image);

    @POST("mobile/postmanager/getAllPosts")
    Observable<FeedResponse> getAllPosts(@Body FeedAllRequest feedAllRequest);

    @POST("mobile/postmanager/getUserPosts")
    Observable<FeedResponse> getSpecUserPosts(@Body FeedUserRequest feedUserRequest);

    @POST("mobile/postmanager/getAllFollowingPosts")
    Observable<FeedResponse> getFollowingPosts(@Body FeedFollowingRequest followingRequest);

    @POST("mobile/postmanager/getSosPosts")
    Observable<FeedResponse> getSosPosts(@Body FeedSosRequest feedSosRequest);

    @POST("mobile/postmanager/postLike")
    Observable<FeedBackResponse> like(@Body FeedActionRequest actionRequest);

    @POST("mobile/postmanager/postUnLike")
    Observable<FeedBackResponse> unlike(@Body FeedActionRequest actionRequest);

    @POST("mobile/postmanager/postFavorite")
    Observable<FeedBackResponse> favorite(@Body FeedActionRequest actionRequest);

    @POST("mobile/postmanager/postUnFavorite")
    Observable<FeedBackResponse> unFavorite(@Body FeedActionRequest actionRequest);

    @POST("mobile/postmanager/postComment")
    Observable<Object> createUpdateComment(@Body FeedCreateUpdateRequest actionRequest);

    @POST("mobile/postmanager/deletePostComment")
    Observable<Object> deleteComment(@Body CommentDeleteRequest actionRequest);

    @POST("mobile/postmanager/getPostComments")
    Observable<CommentDtoResponse> getAllComents(@Body CommentRequest actionRequest);

    @POST("mobile/postmanager/postShare")
    Observable<Object> share(@Body FeedShareRequest shareRequest);

    @POST("mobile/postmanager/postReport")
    Observable<Object> report(@Body FeedReportRequest reportRequest);
}
