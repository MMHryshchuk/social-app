package com.clearsoft.welivre.core.mvp;

import android.os.Bundle;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;



public abstract class BasePresenter<V> implements Presenter<V> {
    private V mView;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    public void onRestore(Bundle savedInstanceState) {

    }

    public void onSaveInstance(Bundle output){

    }

    @Override
    public final void attachView(V view) {
        mView = view;
        onViewAttached();
    }

    @Override
    public final void detachView() {
        onViewDetached();
        mView = null;
        if (mCompositeDisposable != null){
            mCompositeDisposable.dispose();
            mCompositeDisposable = new CompositeDisposable();
        }
    }

    protected void onViewAttached(){

    }

    protected void onViewDetached(){

    }

    protected void addSubscription(Disposable subscriber){
        mCompositeDisposable.add(subscriber);
    }

    protected void cleanSubscriptions(){
        if (mCompositeDisposable.isDisposed()){
            mCompositeDisposable.dispose();
            mCompositeDisposable.clear();
        }
        mCompositeDisposable = new CompositeDisposable();
    }

    public final V getView() {
        return mView;
    }
}
