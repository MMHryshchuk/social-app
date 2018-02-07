package com.clearsoft.welivre.core.mvp;

import android.os.Bundle;


public interface Presenter<V> {
    void onRestore(Bundle savedInstanceState);
    void onSaveInstance(Bundle outputState);
    void attachView(V view);
    void detachView();
    V getView();
}
