package com.clearsoft.welivre.domain.api;

import android.graphics.Bitmap;
import android.util.Base64;

import com.clearsoft.welivre.core.android.AuthRetrofit;
import com.clearsoft.welivre.core.android.UploadRetrofit;
import com.clearsoft.welivre.core.utils.BitmapHelper;
import com.clearsoft.welivre.core.utils.StringUtils;
import com.clearsoft.welivre.domain.api.request.ImageUploadRequest;

import java.io.ByteArrayOutputStream;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by on 28.07.17.
 */

public class ImageApi {
    private IImageApi iImageApi;

    public ImageApi(UploadRetrofit uploadRetrofit) {
        this.iImageApi = uploadRetrofit.create(IImageApi.class);
    }


    public Observable<Object> uploadImage(String path) {
        return Observable.just(path)
                .flatMap(s -> {
                    if (StringUtils.isNullEmpty(path)) {
                        return Observable.just(new Object());
                    }

                    Bitmap bm = BitmapHelper.decodeSampledBitmapFromFile(path, 500, 500);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bm.compress(Bitmap.CompressFormat.JPEG, 80, baos);

                    byte[] b = baos.toByteArray();
                    String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);

                    if (!StringUtils.isNullEmpty(encodedImage)) {
                        return iImageApi.uploadImage(new ImageUploadRequest("data:image/jpg;base64," + encodedImage));
                    } else {
                        return Observable.just(new Object());
                    }
                });
    }


    protected interface IImageApi {
        @POST("posts")
        Observable<Object> uploadImage(
                @Body ImageUploadRequest request);
    }
}
