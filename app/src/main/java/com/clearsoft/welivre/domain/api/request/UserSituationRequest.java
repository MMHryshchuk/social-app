package com.clearsoft.welivre.domain.api.request;

/**
 * Created by on 04.07.17.
 */

public class UserSituationRequest {

    String user_id;
    int  situation;
    String ciga_daily_num;
    String  ciga_pack_cost;
    String  ciga_wakeup_minutes;
    String  ciga_start_age;
    String birthday;
    String  gender;

    public UserSituationRequest() {
    }

    public UserSituationRequest(String user_id, int situation, String ciga_daily_num, String ciga_pack_cost, String ciga_wakeup_minutes, String ciga_start_age, String birthday, String gender) {
        this.user_id = user_id;
        this.situation = situation;
        this.ciga_daily_num = ciga_daily_num;
        this.ciga_pack_cost = ciga_pack_cost;
        this.ciga_wakeup_minutes = ciga_wakeup_minutes;
        this.ciga_start_age = ciga_start_age;
        this.birthday = birthday;
        this.gender = gender;
    }
}
