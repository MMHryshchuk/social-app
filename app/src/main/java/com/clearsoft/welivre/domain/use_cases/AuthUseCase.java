package com.clearsoft.welivre.domain.use_cases;

import com.clearsoft.welivre.domain.api.dto.UserDto;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.Observable;

/**
 * Created by on 04.07.17.
 */

public interface AuthUseCase {

    Observable<String> login(String email, String  password);
    Observable<String> register(String email, String name, String password,String lang, String token);
    Observable<Object> reset(String email, String lang);
    Observable<Object> logout();
}
