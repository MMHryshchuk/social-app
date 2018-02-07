package com.clearsoft.welivre.domain.mappers;

import com.google.gson.Gson;

import javax.inject.Inject;


/**
 * Created by on 08.11.16.
 */

public class ErrorMapper {

    private Gson gson;

    @Inject
    public ErrorMapper(Gson gson) {
        this.gson = gson;
    }


}
