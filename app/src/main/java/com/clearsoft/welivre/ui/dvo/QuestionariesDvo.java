package com.clearsoft.welivre.ui.dvo;

/**
 * Created by on 15.08.17.
 */

public class QuestionariesDvo {
    private int situation;
    private String cigaDailyNum;
    private String cigaPackCost;
    private String cigaWakeup;
    private String cigaStart;
    private String birthday;
    private String gender;

    public QuestionariesDvo(int situation, String cigaDailyNum, String cigaPackCost, String cigaWakeup, String cigaStart, String birthday, String gender) {
        this.situation = situation;
        this.cigaDailyNum = cigaDailyNum;
        this.cigaPackCost = cigaPackCost;
        this.cigaWakeup = cigaWakeup;
        this.cigaStart = cigaStart;
        this.birthday = birthday;
        this.gender = gender;
    }

    public int getSituation() {
        return situation;
    }

    public String getCigaDailyNum() {
        return cigaDailyNum;
    }

    public String getCigaPackCost() {
        return cigaPackCost;
    }

    public String getCigaWakeup() {
        return cigaWakeup;
    }

    public String getCigaStart() {
        return cigaStart;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getGender() {
        return gender;
    }
}
