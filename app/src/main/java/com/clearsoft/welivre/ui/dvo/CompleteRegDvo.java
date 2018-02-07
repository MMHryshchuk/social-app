package com.clearsoft.welivre.ui.dvo;

/**
 * Created by on 04.07.17.
 */

public class CompleteRegDvo {

    private int situation;
    private String cigaDayliNum;
    private String cigaPackCost;
    private String cigaWakeUpMinutes;


    public CompleteRegDvo() {
    }

    public CompleteRegDvo(int situation, String cigaDayliNum, String cigaPackCost, String cigaWakeUpMinutes) {
        this.situation = situation;
        this.cigaDayliNum = cigaDayliNum;
        this.cigaPackCost = cigaPackCost;
        this.cigaWakeUpMinutes = cigaWakeUpMinutes;

    }

    public int getSituation() {
        return situation;
    }

    public void setSituation(int situation) {
        this.situation = situation;
    }

    public String getCigaDayliNum() {
        return cigaDayliNum;
    }

    public void setCigaDayliNum(String cigaDayliNum) {
        this.cigaDayliNum = cigaDayliNum;
    }

    public String getCigaPackCost() {
        return cigaPackCost;
    }

    public void setCigaPackCost(String cigaPackCost) {
        this.cigaPackCost = cigaPackCost;
    }

    public String getCigaWakeUpMinutes() {
        return cigaWakeUpMinutes;
    }

    public void setCigaWakeUpMinutes(String cigaWakeUpMinutes) {
        this.cigaWakeUpMinutes = cigaWakeUpMinutes;
    }


}
