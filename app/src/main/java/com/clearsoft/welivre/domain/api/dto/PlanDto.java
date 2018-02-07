package com.clearsoft.welivre.domain.api.dto;

/**
 * Created by on 20.07.17.
 */

public class PlanDto {

    int id;
    String user_id;
    String quit_date;
    String motivations_ids;
    String triggers_ids;
    String cravings_ids;
    String assistances_ids;
    String p_motivation_photo;
    String p_motivation_video;
    String p_motivation_audio;
    String relapsed;
    String timestamp;

    public PlanDto() {
    }

    public int getId() {
        return id;
    }

    public String getUserId() {
        return user_id;
    }

    public String getQuitDate() {
        return quit_date;
    }

    public String getMotivationsIds() {
        return motivations_ids;
    }

    public String getTriggersIds() {
        return triggers_ids;
    }

    public String getCravingsIds() {
        return cravings_ids;
    }

    public String getAssistancesIds() {
        return assistances_ids;
    }

    public String getP_motivationPhoto() {
        return p_motivation_photo;
    }

    public String getP_motivationVideo() {
        return p_motivation_video;
    }

    public String getP_motivationAudio() {
        return p_motivation_audio;
    }

    public String getRelapsed() {
        return relapsed;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
