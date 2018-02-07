package com.clearsoft.welivre.ui.dvo;

/**
 * Created by on 20.07.17.
 */

public class PlanDvo {

    private String date;
    private String motivationIds;
    private String troggerIds;
    private String cravingIds;
    private String assistanceIds;
    private String relaplsed;
    private long milisec;

    public PlanDvo() {
    }

    public PlanDvo(String date, String motivationIds, String troggerIds, String cravingIds, String assistanceIds) {
        this.date = date;
        this.motivationIds = motivationIds;
        this.troggerIds = troggerIds;
        this.cravingIds = cravingIds;
        this.assistanceIds = assistanceIds;
    }

    public PlanDvo(String date, String motivationIds, String troggerIds, String cravingIds, String assistanceIds, String relaplsed) {
        this.date = date;
        this.motivationIds = motivationIds;
        this.troggerIds = troggerIds;
        this.cravingIds = cravingIds;
        this.assistanceIds = assistanceIds;
        this.relaplsed = relaplsed;
    }

    public PlanDvo(String date, String motivationIds, String troggerIds, String cravingIds, String assistanceIds, String relaplsed, long milisec) {
        this.date = date;
        this.motivationIds = motivationIds;
        this.troggerIds = troggerIds;
        this.cravingIds = cravingIds;
        this.assistanceIds = assistanceIds;
        this.relaplsed = relaplsed;
        this.milisec = milisec;
    }

    public long getMilisec() {
        return milisec;
    }

    public void setMilisec(long milisec) {
        this.milisec = milisec;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMotivationIds() {
        return motivationIds;
    }

    public void setMotivationIds(String motivationIds) {
        this.motivationIds = motivationIds;
    }

    public String getTroggerIds() {
        return troggerIds;
    }

    public void setTroggerIds(String troggerIds) {
        this.troggerIds = troggerIds;
    }

    public String getCravingIds() {
        return cravingIds;
    }

    public void setCravingIds(String cravingIds) {
        this.cravingIds = cravingIds;
    }

    public String getAssistanceIds() {
        return assistanceIds;
    }

    public void setAssistanceIds(String assistanceIds) {
        this.assistanceIds = assistanceIds;
    }

    public String getRelaplsed() {
        return relaplsed;
    }

    public void setRelaplsed(String relaplsed) {
        this.relaplsed = relaplsed;
    }
}
