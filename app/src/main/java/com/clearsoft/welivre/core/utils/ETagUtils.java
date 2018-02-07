package com.clearsoft.welivre.core.utils;

import okhttp3.Headers;
import retrofit2.Response;

/**
 * Created by vladimir on 07.09.16.
 */
public class ETagUtils {

    public static String parseEtag(Response response){
        Headers headers = response.headers();
        return headers.get("ETag");
    }

}
