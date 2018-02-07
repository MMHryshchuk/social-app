package com.clearsoft.welivre.ui.di;

import com.clearsoft.welivre.core.di.scope.PerActivity;
import com.clearsoft.welivre.ui.screens.article.ArticleActivity;
import com.clearsoft.welivre.ui.screens.article.ArticlePresenter;
import com.clearsoft.welivre.ui.screens.article.ArticlePresenterImpl;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;

/**
 * Created by on 30.06.17.
 */

@Subcomponent(modules = ArticleComponent.Module.class)
@PerActivity
public interface ArticleComponent {

    void inject(ArticleActivity activity);

    @dagger.Module
    class Module{

        @Provides
        @PerActivity
        ArticlePresenter provideArticlePresenter(){
            return new ArticlePresenterImpl();
        }
    }
}
