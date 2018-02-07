package com.clearsoft.welivre.ui.dvo;

/**
 * Created by on 17.08.17.
 */

public class HealthDvo {
    private int min20;
    private int hour2;
    private int hour8;
    private int hour12;
    private int days2;
    private int week3;
    private int month1;
    private int year1;
    private int year2;
    private int year10;

    public HealthDvo() {
    }

    public HealthDvo(int min20, int hour2, int hour8, int hour12, int days2, int week3, int month1, int year1, int year2, int year10) {
        this.min20 = min20 > 100 ? 100 : min20;
        this.hour2 = hour2 > 100 ? 100 : hour2;
        this.hour8 = hour8 > 100 ? 100 : hour8;
        this.hour12 = hour12 > 100 ? 100 : hour12;
        this.days2 = days2 > 100 ? 100 : days2;
        this.week3 = week3 > 100 ? 100 : week3;
        this.month1 = month1 > 100 ? 100 : month1;
        this.year1 = year1 > 100 ? 100 : year1;
        this.year2 = year2 > 100 ? 100 : year2;
        this.year10 = year10 > 100 ? 100 : year10;
    }

    public int getMin20() {
        return min20;
    }

    public void setMin20(int min20) {
        this.min20 = min20;
    }

    public int getHour2() {
        return hour2;
    }

    public void setHour2(int hour2) {
        this.hour2 = hour2;
    }

    public int getHour8() {
        return hour8;
    }

    public void setHour8(int hour8) {
        this.hour8 = hour8;
    }

    public int getHour12() {
        return hour12;
    }

    public void setHour12(int hour12) {
        this.hour12 = hour12;
    }

    public int getDays2() {
        return days2;
    }

    public void setDays2(int days2) {
        this.days2 = days2;
    }

    public int getWeek3() {
        return week3;
    }

    public void setWeek3(int week3) {
        this.week3 = week3;
    }

    public int getMonth1() {
        return month1;
    }

    public void setMonth1(int month1) {
        this.month1 = month1;
    }

    public int getYear1() {
        return year1;
    }

    public void setYear1(int year1) {
        this.year1 = year1;
    }

    public int getYear2() {
        return year2;
    }

    public void setYear2(int year2) {
        this.year2 = year2;
    }

    public int getYear10() {
        return year10;
    }

    public void setYear10(int year10) {
        this.year10 = year10;
    }
}
