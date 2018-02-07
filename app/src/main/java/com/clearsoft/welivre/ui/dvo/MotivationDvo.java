package com.clearsoft.welivre.ui.dvo;

/**
 * Created by on 19.07.17.
 */

public class MotivationDvo {

    private int id;
    private String name;
    private int image;
    private boolean isActive;

    public MotivationDvo() {
    }

    public MotivationDvo(int id, String name, int image) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.isActive = false;
    }

    public MotivationDvo(int id, String name, int image, boolean isActive) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}
