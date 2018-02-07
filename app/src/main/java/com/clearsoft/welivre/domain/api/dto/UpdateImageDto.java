package com.clearsoft.welivre.domain.api.dto;

/**
 * Created by on 28.08.17.
 */

public class UpdateImageDto {

    String user_id;
    String image_path;

    public UpdateImageDto() {
    }

    public UpdateImageDto(String user_id, String image_path) {
        this.user_id = user_id;
        this.image_path = image_path;
    }

    public String getUserId() {
        return user_id;
    }

    public String getImagePath() {
        return image_path;
    }
}
