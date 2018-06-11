package com.troy.androidrc.model;

/**
 * Created by Troy on 2018/6/9.
 */

public interface BaseViewModel<T> {

    T loadData();

    void clearData();

}
