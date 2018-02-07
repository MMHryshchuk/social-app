package com.clearsoft.welivre.domain.api;

import com.clearsoft.welivre.domain.api.request.ContentCategoriesRequest;
import com.clearsoft.welivre.domain.api.request.ContentFavoriteRequest;
import com.clearsoft.welivre.domain.api.request.ContentRateRequest;
import com.clearsoft.welivre.domain.api.request.ContentRequest;
import com.clearsoft.welivre.domain.api.request.FavoritesContentRequest;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by on 29.08.17.
 */

public interface ContentApi {

    @POST("mobile/contentmanager/getContentCategories")
    Observable<Object> getContentCategories(@Body ContentCategoriesRequest request);

    @POST("mobile/contentmanager/getContents")
    Observable<Object> getContents(@Body ContentRequest request);

    @POST("mobile/contentmanager/contentFavorite")
    Observable<Object> contentFavorite(@Body ContentFavoriteRequest request);

    @POST("mobile/contentmanager/contentUnFavorite")
    Observable<Object> contentUnFavorite(@Body ContentFavoriteRequest request);

    @POST("mobile/contentmanager/contentRate")
    Observable<Object> contentRate(@Body ContentRateRequest request);

    @POST("mobile/contentmanager/getFavoriteContents")
    Observable<Object> getFavoriteContents(@Body FavoritesContentRequest request);

}
