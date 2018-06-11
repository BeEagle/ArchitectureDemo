package com.troy.androidrc.model;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.troy.androidrc.utils.LogUtil;
import com.troy.androidrc.utils.RandomUtil;

/**
 * Created by Troy on 2018/6/9.
 */

public class UserViewModel extends ViewModel implements BaseViewModel<User>{

    private String TAG = UserViewModel.class.getSimpleName();

    private MutableLiveData<User> liveUser;

    public MutableLiveData<User> getData(){
        if(liveUser == null){
            liveUser = new MutableLiveData<User>();
        }

        liveUser.setValue(loadData());
        return this.liveUser;
    }

    public void changeData(){
        if(liveUser != null){
            liveUser.setValue(loadData());
        }
    }

    @Override
    public User loadData() {
        User user = new User();
        user.userId = RandomUtil.getRandomNumber();
        user.name = RandomUtil.getChineseName();
        user.phone = RandomUtil.getRandomPhone();
        LogUtil.i(TAG, "loadData(): " + user.toString());
        return user;
    }

    @Override
    public void clearData() {

    }
}
