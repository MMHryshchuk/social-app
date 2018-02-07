package com.clearsoft.welivre.domain.repository;

public interface PreferenceRepository {

    void saveAccessToken(String token);
    void saveUserId(String id);
    void saveUserName(String name);
    void saveUserEmail(String email);
    void saveUserPassword(String password);
    void saveUserImage(String image);
    void saveUserLanguage(String lang);
    void saveUserAbout(String about);
    void saveUserDevice(String device);
    void saveUserFollows(String follows);
    void saveUserFollowed(String followed);
    void saveUserPosts(String posts);
    void saveUserSituation(String situation);
    void saveUserCigaDayliNum(String ciga);
    void saveUserCigaPackCost(String ciga);
    void saveUserCigaWakeupMinutes(String cige);
    void saveUserCigaStartAge(String ciga);
    void saveUserBirthday(String birthday);
    void saveUserGender(String gender);

    void saveUserStartNoSmoke(long time);



    void saveAllLastSavedPage(long page);
    void saveFollowingLastSavedPage(long page);
    void saveSosLastSavedPage(long page);
    void saveUserLastSavedPage(long page);
    void saveUserPlanId(int planId);
    void saveCommentLastPage(long page);
    void saveHideUsersId(String userId);

    String getAccessToken();
    String getUserId();
    String getUserName();
    String getUserEmail();
    String getUserImage();
    String getUserLang();
    String getUserPassword();
    String getUserAbout();
    String getUserDevice();
    String getUserFollows();
    String getUserFollowed();
    String getUserPosts();
    String getUserSituation();
    String getUserCigaDayliNum();
    String getUserCigaPackCost();
    String getUserCigaWakeupMin();
    String getUserCigaStartAge();
    String getUserBirthday();
    String getUserGender();

    long getUserStartSmoke();

    long getAllLastSavePage();
    long getFollowingLastSavePage();
    long getSosLastSavePage();
    long getUserLastSavePage();
    long getCommentLastSavePage();
    int getUserPlanId();
    String getHideUserdsIds();

    void clear();

    boolean isSavedInExternalStorage();
    boolean canChangeSavedInExternalStorage();
    void savedInExternalStorage(boolean canSave);
    void setCanChangeSavedInExternalStorage(boolean canSave);
}
