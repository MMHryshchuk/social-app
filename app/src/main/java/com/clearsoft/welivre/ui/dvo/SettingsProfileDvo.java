package com.clearsoft.welivre.ui.dvo;

import android.net.Uri;

/**
 * Created by on 15.08.17.
 */

public class SettingsProfileDvo {

    private String name;
    private String email;
    private String about;
    private String image;

    public SettingsProfileDvo() {
    }



    public SettingsProfileDvo(String name, String email, String about, String image) {
        this.name = name;
        this.email = email;
        this.about = about;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
