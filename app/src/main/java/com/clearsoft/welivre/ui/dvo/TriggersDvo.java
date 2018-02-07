package com.clearsoft.welivre.ui.dvo;

/**
 * Created by on 10.08.17.
 */

public class TriggersDvo {

    private int triggersId;
    private String  triggersName;
    private int drawable;

    public TriggersDvo() {
    }

    public TriggersDvo(int triggersId, String triggersName, int drawable) {
        this.triggersId = triggersId;
        this.triggersName = triggersName;
        this.drawable = drawable;
    }

    public int getTriggersId() {
        return triggersId;
    }

    public void setTriggersId(int triggersId) {
        this.triggersId = triggersId;
    }

    public String getTriggersName() {
        return triggersName;
    }

    public void setTriggersName(String triggersName) {
        this.triggersName = triggersName;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }
}
