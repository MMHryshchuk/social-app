package com.clearsoft.welivre.core.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Fragment, which wrap setup Presenter logic.
 * This parent need, when you need create presenter in fragment
 */
public abstract class PresenterViewMvpFragment<F extends Presenter> extends ViewMvpFragment<F> {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attachPresenter(setupPresenter());
    }

    public abstract Presenter setupPresenter();
}
