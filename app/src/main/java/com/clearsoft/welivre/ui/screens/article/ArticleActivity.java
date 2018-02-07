package com.clearsoft.welivre.ui.screens.article;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.android.BaseActivity;
import com.clearsoft.welivre.core.android.PanelActivity;
import com.clearsoft.welivre.ui.di.ArticleComponent;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by on 30.06.17.
 */

public class ArticleActivity  extends PanelActivity implements ArticleView{


    @Inject
    ArticlePresenter mArticlePresenter;

    public static void start(Activity activity){
        Intent intent = new Intent(activity, ArticleActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        ButterKnife.bind(this);
        setupDagger();

    }

    @Override
    protected void onStart() {
        super.onStart();
        mArticlePresenter.attachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mArticlePresenter.detachView();
    }

    private void setupDagger(){
        App.getApp(this)
                .getAppComponent()
                .plus(new ArticleComponent.Module())
                .inject(this);
    }

    @Override
    public void close() {
        finish();
    }



    @OnClick(R.id.activity_article_back_btn)
    public void onBackClick(){
        mArticlePresenter.onBackClick();
    }


}
