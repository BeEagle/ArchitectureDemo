package com.troy.androidrc.mvp;

import android.arch.lifecycle.LifecycleObserver;

/**
 * Created by Troy on 2018/6/9.
 */

public interface BaseLocationPresenter extends BasePresenter{

    void onStartLocation();

    void onStopLocation();

}
