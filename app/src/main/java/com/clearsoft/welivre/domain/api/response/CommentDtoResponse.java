package com.clearsoft.welivre.domain.api.response;

import com.clearsoft.welivre.domain.api.dto.CommentDto;

import java.util.List;

/**
 * Created by on 26.07.17.
 */

public class CommentDtoResponse {

    int status;
    List<CommentDto> result;

    public CommentDtoResponse() {
    }

    public CommentDtoResponse(int status, List<CommentDto>  result) {
        this.status = status;
        this.result = result;
    }

    public int getStatus() {

        return status;
    }

    public List<CommentDto>  getResult() {
        return result;
    }
}
