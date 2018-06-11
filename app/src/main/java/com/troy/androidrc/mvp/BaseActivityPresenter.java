package com.troy.androidrc.mvp;

/**
 * Created by Troy on 2018/6/5.
 */

public interface BaseActivityPresenter extends BasePresenter{

    void onCreate();

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

}
