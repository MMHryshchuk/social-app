package com.clearsoft.welivre.core.executors;


import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by vladimir on 02.06.16.
 *
 * Thread abstraction created to change the execution context from any thread to any other thread.
 * Useful to encapsulate a UI Thread for example, since some job will be done in background, an
 * implementation of this interface will change context and update the UI.
 */
public interface PostExecutionThread {
    Scheduler getScheduler();

    class DefaultWorker implements PostExecutionThread{

        @Override
        public Scheduler getScheduler() {
            return AndroidSchedulers.mainThread();
        }
    }
}
