package com.clearsoft.welivre.ui.dvo;

/**
 * Created by on 09.08.17.
 */

public class AssistancesDvo {

    private int assistancesId;
    private String name;
    private int drawable;

    public AssistancesDvo() {
    }

    public AssistancesDvo(int assistancesId, String name, int drawable) {
        this.assistancesId = assistancesId;
        this.name = name;
        this.drawable = drawable;
    }

    public int getAssistancesId() {
        return assistancesId;
    }

    public void setAssistancesId(int assistancesId) {
        this.assistancesId = assistancesId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }
}
