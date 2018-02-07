package com.clearsoft.welivre.ui.dvo;

/**
 * Created by on 17.07.17.
 */

public class ChatItemDvo {

    private String messageTxt;
    private String messageImg;
    private String messageTime;
    private boolean isMy;

    public ChatItemDvo(String messageTxt, String messageImg, String messageTime, boolean isMy) {
        this.messageTxt = messageTxt;
        this.messageImg = messageImg;
        this.messageTime = messageTime;
        this.isMy = isMy;
    }

    public String getMessageTxt() {
        return messageTxt;
    }

    public void setMessageTxt(String messageTxt) {
        this.messageTxt = messageTxt;
    }

    public String getMessageImg() {
        return messageImg;
    }

    public void setMessageImg(String messageImg) {
        this.messageImg = messageImg;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    public boolean isMy() {
        return isMy;
    }

    public void setMy(boolean my) {
        isMy = my;
    }
}
