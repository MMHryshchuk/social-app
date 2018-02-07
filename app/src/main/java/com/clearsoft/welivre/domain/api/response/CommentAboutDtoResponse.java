package com.clearsoft.welivre.domain.api.response;

import com.clearsoft.welivre.domain.api.dto.CommentAboutDto;
import com.clearsoft.welivre.domain.api.dto.CommentDto;

import java.util.List;

/**
 * Created by on 26.07.17.
 */

public class CommentAboutDtoResponse {

    int status;
    List<CommentAboutDto> result;

    public CommentAboutDtoResponse() {
    }

    public CommentAboutDtoResponse(int status, List<CommentAboutDto>  result) {
        this.status = status;
        this.result = result;
    }

    public int getStatus() {

        return status;
    }

    public List<CommentAboutDto>  getResult() {
        return result;
    }
}
