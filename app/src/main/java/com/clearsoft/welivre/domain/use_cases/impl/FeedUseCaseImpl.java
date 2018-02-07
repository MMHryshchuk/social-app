package com.clearsoft.welivre.domain.use_cases.impl;

import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;

import com.annimon.stream.Stream;
import com.clearsoft.welivre.core.android.AuthRetrofit;
import com.clearsoft.welivre.core.executors.PostExecutionThread;
import com.clearsoft.welivre.core.executors.ThreadExecutor;
import com.clearsoft.welivre.core.utils.BitmapHelper;
import com.clearsoft.welivre.core.utils.StringUtils;
import com.clearsoft.welivre.domain.api.FeedApi;
import com.clearsoft.welivre.domain.api.request.CommentDeleteRequest;
import com.clearsoft.welivre.domain.api.request.CommentRequest;
import com.clearsoft.welivre.domain.api.request.FeedActionRequest;
import com.clearsoft.welivre.domain.api.request.FeedAllRequest;
import com.clearsoft.welivre.domain.api.request.FeedCreateUpdateRequest;
import com.clearsoft.welivre.domain.api.request.FeedFollowingRequest;
import com.clearsoft.welivre.domain.api.request.FeedSosRequest;
import com.clearsoft.welivre.domain.api.request.FeedUserRequest;
import com.clearsoft.welivre.domain.api.request.ImageUploadRequest;
import com.clearsoft.welivre.domain.api.request.NewPostRequest;
import com.clearsoft.welivre.domain.api.response.FeedBackResponse;
import com.clearsoft.welivre.domain.api.response.FeedImgResponse;
import com.clearsoft.welivre.domain.mappers.ErrorMapper;
import com.clearsoft.welivre.domain.mappers.FeedMapper;
import com.clearsoft.welivre.domain.repository.FeedRepository;
import com.clearsoft.welivre.domain.repository.PreferenceRepository;
import com.clearsoft.welivre.domain.repository.SaveEntityRepository;
import com.clearsoft.welivre.domain.repository.impl.SaveEntityRepositoryImpl;
import com.clearsoft.welivre.domain.use_cases.FeedUseCase;
import com.clearsoft.welivre.ui.dvo.CommentDvo;
import com.clearsoft.welivre.ui.dvo.CommentsListDvo;
import com.clearsoft.welivre.ui.dvo.FeedDvo;
import com.clearsoft.welivre.ui.dvo.FeedListDvo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Retrofit;

/**
 * Created by on 04.07.17.
 */

public class FeedUseCaseImpl implements FeedUseCase {


    private ThreadExecutor threadExecutor;
    private PostExecutionThread postExecutionThread;
    private FeedApi feedApi;
    private PreferenceRepository preferenceRepository;
    private SaveEntityRepository saveEntityRepository;
    private FeedRepository feedRepository;
    private ErrorMapper errorMapper;
    private FeedMapper feedMapper;

    public FeedUseCaseImpl(
            ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread,
            Retrofit retrofit,
            AuthRetrofit authRetrofit,
            PreferenceRepository preferenceRepository,
            SaveEntityRepository saveEntityRepository,
            FeedRepository feedRepository,
            ErrorMapper errorMapper,
            FeedMapper feedMapper
    ) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
        this.feedApi = authRetrofit.create(FeedApi.class);
        this.preferenceRepository = preferenceRepository;
        this.saveEntityRepository = saveEntityRepository;
        this.feedRepository = feedRepository;
        this.errorMapper = errorMapper;
        this.feedMapper = feedMapper;
    }


    @Override
    public Observable<FeedListDvo> getAllPost(String lang, long last_index) {
        return /*Observable.concat(
                getAllFeedFromDB()
                        .onErrorReturn(throwable -> null)
                        .subscribeOn(Schedulers.from(threadExecutor))
                        .observeOn(postExecutionThread.getScheduler()),*/
                getAllFeedFromServer(lang, last_index)
                        .subscribeOn(Schedulers.from(threadExecutor))
                        .observeOn(postExecutionThread.getScheduler()
                        );
    }

    @Override
    public Observable<FeedListDvo> getFollowingPost(long last_index, String language) {
        return/* Observable.concat(
                getFollowingFeedFromDB()
                        .subscribeOn(Schedulers.from(threadExecutor))
                        .observeOn(postExecutionThread.getScheduler()),*/
                getFollowingFeedFromServer(last_index, language)
                        .subscribeOn(Schedulers.from(threadExecutor))
                        .observeOn(postExecutionThread.getScheduler()
                        );
    }

    @Override
    public Observable<FeedListDvo> getSosPost(String lang, long last_index) {
        return /*Observable.concat(
                getSosFeedFromDB()
                        .subscribeOn(Schedulers.from(threadExecutor))
                        .observeOn(postExecutionThread.getScheduler()),*/
                getSosFeedFromServer(lang, last_index)
                        .subscribeOn(Schedulers.from(threadExecutor))
                        .observeOn(postExecutionThread.getScheduler()
                        );
    }

    @Override
    public Observable<FeedListDvo> getUserPost(String targetId, long last_index, String language) {
        return /*Observable.concat(
                getUserFeedFromDB()
                        .subscribeOn(Schedulers.from(threadExecutor))
                        .observeOn(postExecutionThread.getScheduler()),*/
                getUserFeedFromServer(targetId,last_index,language)
                        .subscribeOn(Schedulers.from(threadExecutor))
                        .observeOn(postExecutionThread.getScheduler()
                        );
    }

/*    public Observable<String>  dsa(String postTxt, boolean sos, String postImg) {
        int isSos = sos ? 2 : 1;
        return feedApi.newPost(new NewPostRequest(Integer.parseInt(preferenceRepository.getUserId()), postTxt, isSos, "ua"),new MultipartBody.Part())
                .map(feedImgResponse -> feedImgResponse.getResult())
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
    }*/

    @Override
    public Observable<String> newPost(String postTxt, boolean sos, String path, String lang) {
        int isSos = sos ? 2 : 1;
        return Observable.just(path)
                .flatMap(s -> {
                    Map<String, RequestBody> map = new HashMap<>();
                    map.put("user_id", toRequestBody(preferenceRepository.getUserId()));
                    map.put("content_txt", toRequestBody(postTxt));
                    map.put("sos", toRequestBody(String.valueOf(isSos)));
                    map.put("language", toRequestBody(lang));
                    if (StringUtils.isNullEmpty(path)) {
                        return feedApi.newPost(map, null)
                                .map(postResponse -> "done");
                    }
                    File file = new File(path);
                    RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);


                    MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("post_img", file.getName(), reqFile);


                    return feedApi.newPost(map, fileToUpload)
                            .map(postResponse -> "done");

                })
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
    }

    private static RequestBody toRequestBody(String value) {
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), value);
        return body;
    }

    @Override
    public Observable<FeedDvo> likePost(int postId) {

        return feedApi.like(new FeedActionRequest(Integer.parseInt(preferenceRepository.getUserId()), postId))
                .flatMap(feedBackResponse -> feedRepository.getFeed(String.valueOf(postId))
                        .map(feed -> {
                            feed.setPostLiked("1");
                            feed.setPostLikes(String.valueOf(Integer.parseInt(feed.getPostLikes()) + 1));
                            saveEntityRepository.updateFeed(feed);
                            return feedMapper.mapFeedDvo(feed);

                        }))
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
    }

    @Override
    public Observable<FeedDvo> unlikePost(int postId) {
        return feedApi.unlike(new FeedActionRequest(Integer.parseInt(preferenceRepository.getUserId()), postId))
                .flatMap(feedBackResponse -> feedRepository.getFeed(String.valueOf(postId))
                        .map(feed -> {
                            feed.setPostLiked("0");
                            feed.setPostLikes(String.valueOf(Integer.parseInt(feed.getPostLikes()) - 1));
                            saveEntityRepository.updateFeed(feed);
                            return feedMapper.mapFeedDvo(feed);

                        })

                )
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());

    }

    @Override
    public Observable<FeedDvo> favoritePost(int postId) {
        return Observable.zip(feedApi.favorite(new FeedActionRequest(Integer.parseInt(preferenceRepository.getUserId()), postId)),
                feedRepository.getFeed(String.valueOf(postId)), (feedBackResponse, feed) -> {
                    if (feedBackResponse.getResult()) {
                        feed.setPostFavorited("1");
                        feed.setPostFavorites(String.valueOf(Integer.parseInt(feed.getPostFavorites()) + 1));
                        saveEntityRepository.updateFeed(feed);
                    }
                    return feedMapper.mapFeedDvo(feed);
                })
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
    }

    @Override
    public Observable<FeedDvo> unFavoritePost(int postId) {
        return Observable.zip(feedApi.unFavorite(new FeedActionRequest(Integer.parseInt(preferenceRepository.getUserId()), postId)),
                feedRepository.getFeed(String.valueOf(postId)), (feedBackResponse, feed) -> {
                    if (feedBackResponse.getResult()) {
                        feed.setPostFavorited("0");
                        feed.setPostFavorites(String.valueOf(Integer.parseInt(feed.getPostFavorites()) - 1));
                        saveEntityRepository.updateFeed(feed);
                    }
                    return feedMapper.mapFeedDvo(feed);
                })
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
    }

    @Override
    public Observable<Object> postComment(String txt, int postId) {
        return feedApi.createUpdateComment(new FeedCreateUpdateRequest(Integer.parseInt(preferenceRepository.getUserId()), postId, txt))
                .doOnNext(o -> {

                })
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
    }

    @Override
    public Observable<CommentsListDvo> getComments(int postId, long lastPage) {
        return feedApi.getAllComents(new CommentRequest(Integer.parseInt(preferenceRepository.getUserId()), postId, lastPage))
                .doOnNext(commentDtoResponse -> {
                    if (commentDtoResponse.getStatus() == 200 & commentDtoResponse.getResult() != null && !commentDtoResponse.getResult().isEmpty()) {
                        preferenceRepository.saveCommentLastPage(Long.parseLong(commentDtoResponse.getResult().get(commentDtoResponse.getResult().size() - 1).getComment_id()));
                    } else {
                        preferenceRepository.saveCommentLastPage(-1);

                    }
                })
                .map(commentDtoResponse -> {
                    List<CommentDvo> data = new ArrayList<CommentDvo>();
                    Stream.of(commentDtoResponse.getResult()).forEach(commentDto -> {
                        data.add(new CommentDvo(
                                Long.parseLong(commentDto.getUser_id()),
                                commentDto.getUser_name(),
                                commentDto.getUser_avatar(),
                                Long.parseLong(commentDto.getComment_id()),
                                commentDto.getComment_txt(),
                                Long.parseLong(commentDto.getTimestamp())
                        ));
                    });

                    return new CommentsListDvo(data, preferenceRepository.getCommentLastSavePage());
                })
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
    }

    @Override
    public Observable<Object> deleteComment(int commentId) {
        return feedApi.deleteComment(new CommentDeleteRequest(Integer.parseInt(preferenceRepository.getUserId()), commentId))
                .doOnNext(o -> {

                })
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
    }

    @Override
    public Observable<FeedListDvo> getAllFavorites() {
        return feedRepository.getAllFavorites()
                .map(feeds -> {
                    List<FeedDvo> feedDvos = new ArrayList<FeedDvo>();
                    Stream.of(feeds).forEach(feed -> {
                        feedDvos.add(feedMapper.mapFeedDvo(feed));
                    });
                    return new FeedListDvo(feedDvos);
                })
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
    }

    @Override
    public Observable<FeedListDvo> hideFromUserAndGetPosts(String userId) {
        String userIds = preferenceRepository.getHideUserdsIds();
        if (StringUtils.isNullEmpty(userIds)) {
            userIds = userIds + userId;
        } else {
            userIds = userIds + "." + userId;
        }
        preferenceRepository.saveHideUsersId(userIds);
       return getAllFeedFromDB();
    }


    /**
     * *******  Get Post From server ********
     **/

    private Observable<FeedListDvo> getAllFeedFromServer(String lang, long last_index) {
        return feedApi.getAllPosts(new FeedAllRequest(Integer.parseInt(preferenceRepository.getUserId()), last_index, lang))
                .doOnNext(feedResponse -> {
                    if (feedResponse.getStatus() == 200 && feedResponse.getResult() != null && !feedResponse.getResult().isEmpty()) {
                        preferenceRepository.saveAllLastSavedPage((long) Integer.parseInt(feedResponse.getResult().get(feedResponse.getResult().size() - 1).getPostId()));
                        saveEntityRepository.saveFeedAndRelatedData(feedResponse, SaveEntityRepository.FEED_ALL);
                    } else {
                        preferenceRepository.saveAllLastSavedPage((long) -1);
                    }
                })
                .flatMap(feedResponse -> getAllFeedFromDB());
    }

    private Observable<FeedListDvo> getFollowingFeedFromServer(long last_index,String language) {
        return feedApi.getFollowingPosts(new FeedFollowingRequest(Integer.parseInt(preferenceRepository.getUserId()), last_index, language))
                .doOnNext(feedResponse -> {
                    if (feedResponse.getStatus() == 200 && feedResponse.getResult() != null && !feedResponse.getResult().isEmpty()) {
                        preferenceRepository.saveFollowingLastSavedPage((long) Integer.parseInt(feedResponse.getResult().get(feedResponse.getResult().size() - 1).getPostId()));
                        saveEntityRepository.saveFeedAndRelatedData(feedResponse, SaveEntityRepository.FEED_FOLLOWING);
                    } else {
                        preferenceRepository.saveFollowingLastSavedPage((long) -1);
                    }
                })
                .flatMap(feedResponse -> getFollowingFeedFromDB());
    }

    private Observable<FeedListDvo> getSosFeedFromServer(String lang, long last_index) {
        return feedApi.getSosPosts(new FeedSosRequest(Integer.parseInt(preferenceRepository.getUserId()), last_index, lang))
                .doOnNext(feedResponse -> {
                    if (feedResponse.getStatus() == 200 && feedResponse.getResult() != null && !feedResponse.getResult().isEmpty()) {
                        preferenceRepository.saveSosLastSavedPage((long) Integer.parseInt(feedResponse.getResult().get(feedResponse.getResult().size() - 1).getPostId()));
                        saveEntityRepository.saveFeedAndRelatedData(feedResponse, SaveEntityRepository.FEED_SOS);
                    } else {
                        preferenceRepository.saveSosLastSavedPage((long) -1);
                    }

                })
                .flatMap(feedResponse -> getSosFeedFromDB());
    }

    private Observable<FeedListDvo> getUserFeedFromServer(String targetId, long last_index, String language) {
        return feedApi.getSpecUserPosts(new FeedUserRequest(Integer.parseInt(preferenceRepository.getUserId()),Integer.parseInt(targetId), last_index,language))
                .doOnNext(feedResponse -> {
                    if (feedResponse.getStatus() == 200 && feedResponse.getResult() != null && !feedResponse.getResult().isEmpty()) {
                        preferenceRepository.saveUserLastSavedPage((long) Integer.parseInt(feedResponse.getResult().get(feedResponse.getResult().size() - 1).getPostId()));
                        saveEntityRepository.saveFeedAndRelatedData(feedResponse, SaveEntityRepository.FEED_USER);
                    } else {
                        preferenceRepository.saveUserLastSavedPage((long) -1);
                    }
                })
                .flatMap(feedResponse -> getUserFeedFromDB(targetId));
    }


    /**
     * *******  Get Post From DataBase ********
     **/

    private Observable<FeedListDvo> getAllFeedFromDB() {
        return feedRepository.getAllFeeds(preferenceRepository.getHideUserdsIds())
                .map(feeds -> {
                    List<FeedDvo> feedList = new ArrayList<>();

                    Stream.of(feeds).forEach(feed -> {

                        feedList.add(feedMapper.mapFeedDvo(feed));
                    });

                    return new FeedListDvo(feedList, preferenceRepository.getAllLastSavePage());
                });

    }

    private Observable<FeedListDvo> getFollowingFeedFromDB() {
        return feedRepository.getFollowingFeeds(preferenceRepository.getHideUserdsIds())
                .map(feeds -> {
                    List<FeedDvo> feedList = new ArrayList<>();
                    Stream.of(feeds).forEach(feed -> {

                        feedList.add(feedMapper.mapFeedDvo(feed));
                    });

                    return new FeedListDvo(feedList, preferenceRepository.getFollowingLastSavePage());
                });

    }

    private Observable<FeedListDvo> getSosFeedFromDB() {
        return feedRepository.getSosFeeds(preferenceRepository.getHideUserdsIds())
                .map(feeds -> {
                    List<FeedDvo> feedList = new ArrayList<>();
                    Stream.of(feeds).forEach(feed -> {

                        feedList.add(feedMapper.mapFeedDvo(feed));
                    });

                    return new FeedListDvo(feedList, preferenceRepository.getSosLastSavePage());
                });

    }

    private Observable<FeedListDvo> getUserFeedFromDB(String userId) {
        return feedRepository.getUserFeeds(userId)
                .map(feeds -> {
                    List<FeedDvo> feedList = new ArrayList<>();
                    Stream.of(feeds).forEach(feed -> {

                        feedList.add(feedMapper.mapFeedDvo(feed));
                    });

                    return new FeedListDvo(feedList, preferenceRepository.getUserLastSavePage());
                });

    }

    private Observable<String> saveHideUserIds(String userId) {
        return Observable.create(e -> {
            String userIds = preferenceRepository.getHideUserdsIds();
            if (StringUtils.isNullEmpty(userIds)) {
                userIds = userIds + userId;
            } else {
                userIds = userIds + "." + userId;
            }
            preferenceRepository.saveHideUsersId(userIds);
            e.onNext(userIds);
            e.onComplete();
        });
    }


}
