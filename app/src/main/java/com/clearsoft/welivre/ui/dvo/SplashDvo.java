package com.clearsoft.welivre.ui.dvo;

/**
 * Created by on 27.06.17.
 */

public class SplashDvo {

    private int image;
    private String tittle;
    private String descriptions;

    public SplashDvo() {
    }

    public SplashDvo(int image, String tittle, String descriptions) {
        this.image = image;
        this.tittle = tittle;
        this.descriptions = descriptions;
    }

    public int getImage() {
        return image;
    }

    public String getTittle() {
        return tittle;
    }

    public String getDescriptions() {
        return descriptions;
    }
}
