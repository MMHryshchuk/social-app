package com.clearsoft.welivre.domain.use_cases;

import io.reactivex.Observable;

/**
 * Created by on 28.07.17.
 */

public interface UploadUseCase {

    Observable<String> uploadPostImage(String path);
    Observable<String> uploadAvatarImage(String path);
}
