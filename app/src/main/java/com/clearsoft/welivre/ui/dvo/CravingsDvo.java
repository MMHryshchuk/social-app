package com.clearsoft.welivre.ui.dvo;

/**
 * Created by on 09.08.17.
 */

public class CravingsDvo {

    private int cravingId;
    private String title;
    private String txt;
    private int drawable;

    public CravingsDvo() {
    }

    public CravingsDvo(int cravingId, String title, String txt, int drawable) {
        this.cravingId = cravingId;
        this.title = title;
        this.txt = txt;
        this.drawable = drawable;
    }

    public int getCravingId() {
        return cravingId;
    }

    public void setCravingId(int cravingId) {
        this.cravingId = cravingId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }
}
