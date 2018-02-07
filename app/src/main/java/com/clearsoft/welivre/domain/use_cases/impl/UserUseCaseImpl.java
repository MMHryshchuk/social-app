package com.clearsoft.welivre.domain.use_cases.impl;

import com.annimon.stream.Stream;
import com.clearsoft.welivre.core.android.AuthRetrofit;
import com.clearsoft.welivre.core.executors.PostExecutionThread;
import com.clearsoft.welivre.core.executors.ThreadExecutor;
import com.clearsoft.welivre.core.utils.StringUtils;
import com.clearsoft.welivre.domain.api.UserApi;
import com.clearsoft.welivre.domain.api.dto.SpecUserInfoDto;
import com.clearsoft.welivre.domain.api.dto.UserDto;
import com.clearsoft.welivre.domain.api.request.AboutCommentRequest;
import com.clearsoft.welivre.domain.api.request.AboutCrateUpdateRequest;
import com.clearsoft.welivre.domain.api.request.AboutCreateCommentRequest;
import com.clearsoft.welivre.domain.api.request.AboutLikeRequest;
import com.clearsoft.welivre.domain.api.request.AboutRequest;
import com.clearsoft.welivre.domain.api.request.FollowersRequest;
import com.clearsoft.welivre.domain.api.request.FollowingRequest;
import com.clearsoft.welivre.domain.api.request.SmokingRequest;
import com.clearsoft.welivre.domain.api.request.SpecificUserRequest;
import com.clearsoft.welivre.domain.api.request.UnFollowingRequest;
import com.clearsoft.welivre.domain.api.request.UserDeleteRequest;
import com.clearsoft.welivre.domain.api.request.UserSituationRequest;
import com.clearsoft.welivre.domain.api.response.FeedImgResponse;
import com.clearsoft.welivre.domain.mappers.ErrorMapper;
import com.clearsoft.welivre.domain.mappers.FollowMapper;
import com.clearsoft.welivre.domain.repository.FollowRepository;
import com.clearsoft.welivre.domain.repository.PreferenceRepository;
import com.clearsoft.welivre.domain.repository.SaveEntityRepository;
import com.clearsoft.welivre.domain.use_cases.UserUseCase;
import com.clearsoft.welivre.ui.dvo.AboutDvo;
import com.clearsoft.welivre.ui.dvo.CommentDvo;
import com.clearsoft.welivre.ui.dvo.CommentsListDvo;
import com.clearsoft.welivre.ui.dvo.FollowingListDvo;
import com.clearsoft.welivre.ui.dvo.ProfileDvo;
import com.clearsoft.welivre.ui.dvo.QuestionariesDvo;
import com.clearsoft.welivre.ui.dvo.SettingsProfileDvo;

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
 * Created by on 18.07.17.
 */

public class UserUseCaseImpl implements UserUseCase {

    private ThreadExecutor threadExecutor;
    private PostExecutionThread postExecutionThread;
    private UserApi userApi;
    private PreferenceRepository preferenceRepository;
    private FollowRepository followRepository;
    private SaveEntityRepository saveEntityRepository;
    private ErrorMapper errorMapper;
    private FollowMapper followMapper;

    public UserUseCaseImpl(
            ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread,
            Retrofit retrofit,
            AuthRetrofit authRetrofit,
            PreferenceRepository preferenceRepository,
            FollowRepository followRepository,
            SaveEntityRepository saveEntityRepository,
            ErrorMapper errorMapper,
            FollowMapper followMapper
    ) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
        this.userApi = authRetrofit.create(UserApi.class);
        this.preferenceRepository = preferenceRepository;
        this.saveEntityRepository = saveEntityRepository;
        this.errorMapper = errorMapper;
        this.followMapper = followMapper;
    }


    @Override
    public Observable<Object> setUserSituation(int situation, String cigaDayliNum, String cigaPackCost, String cigaWakeUpMinutes, String cigaStartAge, String birthday, String gender) {
        return userApi.setUserSituation(new UserSituationRequest(preferenceRepository.getUserId(), situation, cigaDayliNum, cigaPackCost, cigaWakeUpMinutes, cigaStartAge, birthday, gender))
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
    }

    @Override
    public Observable<Object> setSmokingStatus(String situation) {
        return userApi.setSmokingStatus(new SmokingRequest(Integer.parseInt(preferenceRepository.getUserId()), situation))
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
    }

    @Override
    public Observable<Object> deleteAccaunt() {
        return userApi.deleteUserAccoutn(new UserDeleteRequest(Integer.parseInt(preferenceRepository.getUserId())))
                .doOnNext(o -> {

                })
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
    }

    @Override
    public Observable<FollowingListDvo> getAllFollowers(String userId, long page) {
        if (StringUtils.isNullEmpty(userId))
            userId = preferenceRepository.getUserId();
        return userApi.getAllFollowers(new FollowersRequest(Integer.parseInt(userId), page))
                .doOnNext(o -> {

                })
                .map(response -> new FollowingListDvo(followMapper.mapFollowDvo(response)))
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
    }

    @Override
    public Observable<FollowingListDvo> getAllFollowings(String userId, long page) {
        if (StringUtils.isNullEmpty(userId))
            userId = preferenceRepository.getUserId();
        return userApi.getAllFollowing(new FollowersRequest(Integer.parseInt(userId), page))
                .doOnNext(response -> {
                    if (response.getStatus() == 200 && response.getResult() != null && !response.getResult().isEmpty()) {
                        saveEntityRepository.saveFollow(response);
                    }
                })
                .map(response -> {
                    return new FollowingListDvo(followMapper.mapFollowDvo(response));
                })
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
    }

    @Override
    public Observable<Object> follow(String followId) {
        return userApi.followUser(new FollowingRequest(Integer.parseInt(preferenceRepository.getUserId()), Integer.parseInt(followId)))
                .doOnNext(o -> {

                })
                .map(o -> {
                    return new Object();
                })
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
    }

    @Override
    public Observable<Object> unFollow(String unFollowId) {
        return userApi.unFollowUser(new UnFollowingRequest(Integer.parseInt(preferenceRepository.getUserId()), Integer.parseInt(unFollowId)))
                .doOnNext(o -> {

                })
                .map(o -> {
                    return new Object();
                })
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
    }

    @Override
    public Observable<AboutDvo> getUserAbout(int targetId) {
        return userApi.getUserAbout(new AboutRequest(Integer.parseInt(preferenceRepository.getUserId()), targetId))
                .doOnNext(aboutResponse -> {

                })
                .map(aboutResponse -> new AboutDvo(
                        aboutResponse.getResult().get(0).getUserAbout(),
                        aboutResponse.getResult().get(0).getAboutLikes(),
                        aboutResponse.getResult().get(0).getAboutComments(),
                        aboutResponse.getResult().get(0).getAboutLiked().equals("1"),
                        preferenceRepository.getUserId().equals(String.valueOf(targetId))

                ))
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
    }

    @Override
    public Observable<String> likeAbout(int targetId) {
        return userApi.likeUserAbout(new AboutLikeRequest(targetId, Integer.parseInt(preferenceRepository.getUserId())))
                .doOnNext(feedBackResponse -> {

                })
                .map(feedBackResponse -> {
                    return new String();
                })
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
    }

    @Override
    public Observable<String> unLikeAbout(int targetId) {
        return userApi.unLikeUserAbout(new AboutLikeRequest(targetId, Integer.parseInt(preferenceRepository.getUserId())))
                .doOnNext(feedBackResponse -> {

                })
                .map(feedBackResponse -> {
                    return new String();
                })
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
    }

    @Override
    public Observable<String> createUpdateAbout(String about) {
        return userApi.createUpdateAbout(new AboutCrateUpdateRequest(Integer.parseInt(preferenceRepository.getUserId()), about))
                .doOnNext(feedBackResponse -> {
                    preferenceRepository.saveUserAbout(about);
                })
                .map(feedBackResponse -> new String())
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
    }

    @Override
    public Observable<CommentsListDvo> getAboutComments(int userId, long lastIndex) {
        return userApi.getAboutComments(new AboutCommentRequest(userId, lastIndex))
                .doOnNext(commentDtoResponse -> {
                    if (commentDtoResponse.getStatus() == 200 && !commentDtoResponse.getResult().isEmpty()) {
                        preferenceRepository.saveCommentLastPage(1/*Long.parseLong(commentDtoResponse.getResult().get(commentDtoResponse.getResult().size()-1).getComment_id())*/);
                    } else {
                        preferenceRepository.saveCommentLastPage(-1);
                    }
                })
                .map(commentDtoResponse -> {
                    List<CommentDvo> data = new ArrayList<>();
                    Stream.of(commentDtoResponse.getResult()).forEach(commentDto -> {
                        data.add(new CommentDvo(
                                Long.parseLong(commentDto.getUserId()),
                                commentDto.getUserName(),
                                commentDto.getUserAvatar(),
                                Long.parseLong(commentDto.getUserId()),
                                commentDto.getCommentText(),
                                Long.parseLong(commentDto.getCommentTimestamp())
                        ));
                    });

                    return new CommentsListDvo(data, preferenceRepository.getCommentLastSavePage());
                })
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
    }

    @Override
    public Observable<Object> aboutPostComment(int targetId, String about) {
        return userApi.createAboutComment(new AboutCreateCommentRequest(
                targetId,
                Integer.parseInt(preferenceRepository.getUserId()),
                about))
                .doOnNext(feedBackResponse -> {

                }).map(feedBackResponse -> {
                    return new Object();
                })
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
    }

    @Override
    public Observable<SettingsProfileDvo> getUserProfileInfo() {
        return Observable.create(e -> {
            SettingsProfileDvo dvo = new SettingsProfileDvo();
            dvo.setName(preferenceRepository.getUserName());
            dvo.setEmail(preferenceRepository.getUserEmail());
            dvo.setAbout(preferenceRepository.getUserAbout());
            dvo.setImage(preferenceRepository.getUserImage());
            e.onNext(dvo);
            e.onComplete();
        });
    }

    @Override
    public Observable<SettingsProfileDvo> updateUser(String name, String email, String password, String about, String path) {
        return Observable.just(path)
                .flatMap(s -> {
                    Map<String, RequestBody> map = new HashMap<>();
                    map.put("user_id", toRequestBody(preferenceRepository.getUserId()));
                    map.put("name", toRequestBody(name));
                    map.put("email", toRequestBody(email));
                    map.put("password", toRequestBody(password));
                    map.put("about", toRequestBody(about));
                    if (StringUtils.isNullEmpty(path)) {
                        return userApi.updateUserInfo(map, null)
                                .doOnNext(authDtoResponse -> {
                                    if (authDtoResponse.getStatus() == 200)
                                        saveUserInfoToPref(authDtoResponse.getResult());
                                })
                                .map(o -> new SettingsProfileDvo(
                                        preferenceRepository.getUserName(),
                                        preferenceRepository.getUserEmail(),
                                        preferenceRepository.getUserAbout(),
                                        preferenceRepository.getUserImage()));
                    }
                    File file = new File(path);
                    RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);


                    MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("post_img", file.getName(), reqFile);


                    return userApi.updateUserInfo(map, fileToUpload)
                            .doOnNext(authDtoResponse -> {
                                if (authDtoResponse.getStatus() == 200)
                                    saveUserInfoToPref(authDtoResponse.getResult());
                            })
                            .map(authDtoResponse -> new SettingsProfileDvo(
                                    preferenceRepository.getUserName(),
                                    preferenceRepository.getUserEmail(),
                                    preferenceRepository.getUserAbout(),
                                    preferenceRepository.getUserImage()));

                })
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
    }

    @Override
    public Observable<QuestionariesDvo> getUserQuestionaries() {
        return Observable.create(e -> {
            QuestionariesDvo dvo = new QuestionariesDvo(
                    Integer.parseInt(StringUtils.isNullEmpty(preferenceRepository.getUserSituation()) ? "1" : preferenceRepository.getUserSituation()),
                    preferenceRepository.getUserCigaDayliNum(),
                    preferenceRepository.getUserCigaPackCost(),
                    preferenceRepository.getUserCigaWakeupMin(),
                    preferenceRepository.getUserCigaStartAge(),
                    preferenceRepository.getUserBirthday(),
                    preferenceRepository.getUserGender()
            );
            e.onNext(dvo);
            e.onComplete();
        });
    }

    @Override
    public Observable<ProfileDvo> getSpecificUserInfo(int targetId) {
        return userApi.getSpecificUserInfo(new SpecificUserRequest(Integer.parseInt(preferenceRepository.getUserId()), targetId))
                .doOnNext(profileResponse -> {

                })
                .map(profileResponse -> {
                    SpecUserInfoDto dto = profileResponse.getResult();
                    return new ProfileDvo(
                            Integer.parseInt(dto.getUserId()),
                            dto.getUserEmail(),
                            dto.getUserName(),
                            StringUtils.isNullEmpty(dto.getUserAvatat()) ? "" : "http://welivreapp.com/welivre_ci/upload/profile/" + dto.getUserAvatat(),
                            dto.getUserAbout(),
                            dto.getUserFollowing(),
                            dto.getUserFollowers(),
                            dto.getUserFollowed(),
                            dto.getUserPosts(),
                            dto.getUserPlan(),
                            dto.getUserCigaDailyNum(),
                            dto.getUserCigaPackCost(),
                            dto.getUserCigaStartAge(),
                            dto.getAboutLikes(),
                            dto.getAboutComments(),
                            dto.getAboutLikes()
                    );
                })
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
    }

    @Override
    public Observable<String> uploadAvatar(String path) {
        return Observable.just(path)
                .flatMap(s -> {
                    Map<String, RequestBody> map = new HashMap<>();
                    map.put("user_id", toRequestBody(preferenceRepository.getUserId()));
                    File file = new File(path);
                    RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);

                    MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("post_img", file.getName(), reqFile);
                    return userApi.updateProfileImage(map, fileToUpload)
                            .doOnNext(updateImageResponse -> {
                                preferenceRepository.saveUserImage(updateImageResponse.getResult().getImagePath());
                            })
                            .map(updateImageResponse -> "http://welivreapp.com/welivre_ci/upload/profile/" + updateImageResponse.getResult().getImagePath());

                })
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
    }


    private static RequestBody toRequestBody(String value) {
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), value);
        return body;
    }

    private void saveUserInfoToPref(UserDto dto) {
        preferenceRepository.saveAccessToken(dto.getUserToken());
        preferenceRepository.saveUserId(dto.getUserId());
        preferenceRepository.saveUserName(dto.getUserName());
        preferenceRepository.saveUserEmail(dto.getUserEmail());
        if (!StringUtils.isNullEmpty(dto.getUserAvatat()))
            preferenceRepository.saveUserImage("http://welivreapp.com/welivre_ci/upload/profile/" + dto.getUserAvatat());
        preferenceRepository.saveUserLanguage(dto.getUserLanguage());
        preferenceRepository.saveUserAbout(dto.getUserAbout());
        preferenceRepository.saveUserDevice(dto.getUserDevice());
        preferenceRepository.saveUserFollows(dto.getUserFollows());
        preferenceRepository.saveUserFollowed(dto.getUserFollowed());
        preferenceRepository.saveUserPosts(dto.getUserPosts());
        preferenceRepository.saveUserCigaDayliNum(dto.getUserCigaDailyNum());
        preferenceRepository.saveUserCigaPackCost(dto.getUserCigaPackCost());
        preferenceRepository.saveUserCigaWakeupMinutes(dto.getUserCigaWakeupMinutes());
        preferenceRepository.saveUserCigaStartAge(dto.getUserCigaStartAge());
        preferenceRepository.saveUserBirthday(dto.getUserBirthday());
        preferenceRepository.saveUserGender(dto.getUserGender());
    }

}
