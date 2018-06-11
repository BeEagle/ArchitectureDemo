package com.troy.androidrc.lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

import com.troy.androidrc.mvp.BaseLocationPresenter;
import com.troy.androidrc.utils.LogUtil;

/**
 * Created by Troy on 2018/6/9.
 */

public class LocationLifeObserver implements BaseLocationPresenter, LifecycleObserver {

    private String TAG = LocationLifeObserver.class.getSimpleName();

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    @Override
    public void onStartLocation() {
        LogUtil.i(TAG, "onStartLocation()");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    @Override
    public void onStopLocation() {
        LogUtil.i(TAG, "onStopLocation()");
    }
}
