package com.clearsoft.welivre.core.rx;

import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;


/**
 */
public class SimpleSubscriber<T> implements Subscriber<T> {


    @Override
    public void onError(Throwable e) {
        Log.e("error", "", e);
        e.printStackTrace();
    }

    @Override
    public void onComplete() {

    }


    @Override
    public void onSubscribe(Subscription s) {

    }

    @Override
    public void onNext(T item) {

    }
}
