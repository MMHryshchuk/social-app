package com.clearsoft.welivre.domain.repository.impl;

import android.content.Context;


import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.android.BasePreference;
import com.clearsoft.welivre.domain.repository.PreferenceRepository;

import javax.inject.Inject;

import static java.lang.Boolean.getBoolean;


/**
 * Created by vladimir on 07.06.16.
 */
public class PreferenceRepositoryImpl extends BasePreference implements PreferenceRepository {

    private static final String PREF_USER_ID = "PREF_USER_ID";
    private static final String PREF_USER_EMAIL = "PREF_USER_EMAIL";
    private static final String PREF_USER_PASS = "PREF_USER_PASS";
    private static final String PREF_USER_NAME = "PREF_USER_NAME";
    private static final String PREF_USER_PHOTO = "PREF_USER_PHOTO";
    private static final String PREF_USER_TYPE = "PREF_USER_TYPE";
    private static final String PREF_USER_ABOUT = "PREF_USER_ABOUT";
    private static final String PREF_USER_DEVICE = "PREF_USER_DEVICE";
    private static final String PREF_ACCESS_TOKEN = "PREF_ACCESS_TOKEN";
    private static final String PREF_USER_LANG = "PREF_USER_LANG";
    private static final String PREF_USER_FOLLOWS = "PREF_USER_FOLLOWS";
    private static final String PREF_USER_FOLLOWED = "PREF_USER_FOLLOWED";
    private static final String PREF_USER_POSTS = "PREF_USER_POSTS";
    private static final String PREF_USER_SITUATION = "PREF_USER_SITUATION";
    private static final String PREF_USER_CIGA_DAIL_NUM = "PREF_USER_CIGA_DAIL_NUM";
    private static final String PREF_USER_CIGA_PACK_COST = "PREF_USER_CIGA_PACK_COST";
    private static final String PREF_USER_CIGA_WAKEUP_MIN = "PREF_USER_CIGA_WAKEUP_MIN";
    private static final String PREF_USER_CIGA_START_AGE = "PREF_USER_CIGA_START_AGE";
    private static final String PREF_USER_BIRTHDAY = "PREF_USER_BIRTHDA";
    private static final String PREF_USER_GENDER = "PREF_USER_GENDER";

    private static final String PREF_USER_END_SMOKE = "PREF_USER_END_SMOKE";

    private static final String PREF_PAGE_ALL= "PREF_PAGE_ALL";
    private static final String PREF_PAGE_FOLLOWING= "PREF_PAGE_FOLLOWING";
    private static final String PREF_PAGE_SOS= "PREF_PAGE_SOS";
    private static final String PREF_PAGE_USER = "PREF_PAGE_USER";
    private static final String PREF_COMMENT_PAGE= "PREF_COMMENT_PAGE";
    private static final String PREF_USER_PLAN_ID = "PREF_USER_PLAN_ID";
    private static final String PREFERENCE_SAVE_IN_EXTERAL = "PREFERENCE_SAVE_IN_EXTERAL";
    private static final String PREFERENCE_CAN_CHANGE_SAVE_IN_EXTERAL = "PREFERENCE_CAN_CHANGE_SAVE_IN_EXTERAL";
    private static final String PREF_HIDE_USERS_IDS = "PREF_HIDE_USERS_IDS";

    private App app;

    @Inject
    public PreferenceRepositoryImpl(App app) {
        this.app = app;
    }

    @Override
    protected Context getContext() {
        return app;
    }

    @Override
    public void saveAccessToken(String token) {
        save(PREF_ACCESS_TOKEN, token);
    }

    @Override
    public void saveUserId(String id) {
        save(PREF_USER_ID, id);
    }

    @Override
    public void saveUserName(String name) {
        save(PREF_USER_NAME, name);
    }

    @Override
    public void saveUserEmail(String email) {
        save(PREF_USER_EMAIL, email);
    }

    @Override
    public void saveUserPassword(String password) {
        save(PREF_USER_PASS,password);
    }

    @Override
    public void saveUserImage(String image) {
        save(PREF_USER_PHOTO, image);
    }

    @Override
    public void saveUserLanguage(String lang) {
        save(PREF_USER_LANG,lang);
    }

    @Override
    public void saveUserAbout(String about) {
        save(PREF_USER_ABOUT,about);

    }

    @Override
    public void saveUserDevice(String device) {
        save(PREF_USER_DEVICE,device);
    }

    @Override
    public void saveUserFollows(String follows) {
        save(PREF_USER_FOLLOWS,follows);

    }

    @Override
    public void saveUserFollowed(String followed) {
        save(PREF_USER_FOLLOWED,followed);
    }

    @Override
    public void saveUserPosts(String posts) {
        save(PREF_USER_POSTS,posts);
    }

    @Override
    public void saveUserSituation(String situation) {
        save(PREF_USER_SITUATION,situation);
    }

    @Override
    public void saveUserCigaDayliNum(String ciga) {
        save(PREF_USER_CIGA_DAIL_NUM,ciga);
    }

    @Override
    public void saveUserCigaPackCost(String ciga) {
        save(PREF_USER_CIGA_PACK_COST,ciga);
    }

    @Override
    public void saveUserCigaWakeupMinutes(String cige) {
        save(PREF_USER_CIGA_WAKEUP_MIN,cige);
    }

    @Override
    public void saveUserCigaStartAge(String ciga) {
        save(PREF_USER_CIGA_START_AGE,ciga);
    }

    @Override
    public void saveUserBirthday(String birthday) {
        save(PREF_USER_BIRTHDAY,birthday);
    }

    @Override
    public void saveUserGender(String gender) {
        save(PREF_USER_GENDER,gender);
    }

    @Override
    public void saveUserStartNoSmoke(long time) {
        save(PREF_USER_END_SMOKE,time);
    }

    @Override
    public void saveAllLastSavedPage(long page) {
        save(PREF_PAGE_ALL,page);
    }

    @Override
    public void saveFollowingLastSavedPage(long page) {
        save(PREF_PAGE_FOLLOWING,page);

    }

    @Override
    public void saveSosLastSavedPage(long page) {
        save(PREF_PAGE_SOS,page);

    }

    @Override
    public void saveUserLastSavedPage(long page) {
        save(PREF_PAGE_USER,page);
    }

    @Override
    public void saveUserPlanId(int planId) {
        save(PREF_USER_PLAN_ID,planId);
    }

    @Override
    public void saveCommentLastPage(long page) {
        save(PREF_COMMENT_PAGE, page);
    }

    @Override
    public void saveHideUsersId(String userId) {
        save(PREF_HIDE_USERS_IDS,userId);
    }

    @Override
    public String getAccessToken() {
        return getStr(PREF_ACCESS_TOKEN);
    }

    @Override
    public String getUserId() {
        return getStr(PREF_USER_ID);
    }

    @Override
    public String getUserName() {
        return getStr(PREF_USER_NAME);
    }

    @Override
    public String getUserEmail() {
        return getStr(PREF_USER_EMAIL);
    }

    @Override
    public String getUserImage() {
        return getStr(PREF_USER_PHOTO);
    }

    @Override
    public String getUserLang() {
        return getStr(PREF_USER_LANG);
    }

    @Override
    public String getUserPassword() {
        return getStr(PREF_USER_PASS);
    }

    @Override
    public String getUserAbout() {
        return getStr(PREF_USER_ABOUT);
    }

    @Override
    public String getUserDevice() {
        return getStr(PREF_USER_DEVICE);
    }

    @Override
    public String getUserFollows() {
        return getStr(PREF_USER_FOLLOWS);
    }

    @Override
    public String getUserFollowed() {
        return getStr(PREF_USER_FOLLOWED);
    }

    @Override
    public String getUserPosts() {
        return getStr(PREF_USER_POSTS);
    }

    @Override
    public String getUserSituation() {
        return getStr(PREF_USER_SITUATION);
    }

    @Override
    public String getUserCigaDayliNum() {
        return getStr(PREF_USER_CIGA_DAIL_NUM);

    }

    @Override
    public String getUserCigaPackCost() {
        return getStr(PREF_USER_CIGA_PACK_COST);

    }

    @Override
    public String getUserCigaWakeupMin() {
        return getStr(PREF_USER_CIGA_WAKEUP_MIN);

    }

    @Override
    public String getUserCigaStartAge() {
        return getStr(PREF_USER_CIGA_START_AGE);

    }

    @Override
    public String getUserBirthday() {
        return getStr(PREF_USER_BIRTHDAY);

    }

    @Override
    public String getUserGender() {
        return getStr(PREF_USER_GENDER);

    }

    @Override
    public long getUserStartSmoke() {
        return getLong(PREF_USER_END_SMOKE);
    }

    @Override
    public long getAllLastSavePage() {
        return getLongPage(PREF_PAGE_ALL);
    }

    @Override
    public long getFollowingLastSavePage() {
        return getLongPage(PREF_PAGE_FOLLOWING);
    }

    @Override
    public long getSosLastSavePage() {
        return getLongPage(PREF_PAGE_SOS);

    }

    @Override
    public long getUserLastSavePage() {
        return getLongPage(PREF_PAGE_USER);
    }

    @Override
    public long getCommentLastSavePage() {
        return getLongPage(PREF_COMMENT_PAGE);
    }


    @Override
    public boolean isSavedInExternalStorage() {
        return getBoolean(PREFERENCE_SAVE_IN_EXTERAL, false);
    }

    @Override
    public boolean canChangeSavedInExternalStorage() {
        return getBoolean(PREFERENCE_CAN_CHANGE_SAVE_IN_EXTERAL, true);
    }

    @Override
    public void savedInExternalStorage(boolean canSave) {
        save(PREFERENCE_SAVE_IN_EXTERAL, canSave);
    }

    @Override
    public void setCanChangeSavedInExternalStorage(boolean canSave) {
        save(PREFERENCE_CAN_CHANGE_SAVE_IN_EXTERAL, canSave);
    }


    @Override
    public int getUserPlanId() {
        return getInt(PREF_USER_PLAN_ID);
    }

    @Override
    public String getHideUserdsIds() {
        return getStr(PREF_HIDE_USERS_IDS);
    }

    @Override
    public void clear() {
        getPreference().edit().clear().apply();
    }
}
