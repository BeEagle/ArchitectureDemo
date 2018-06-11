package com.troy.androidrc.db.address;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.troy.androidrc.utils.RandomUtil;

/**
 * Created by Troy on 2018/6/11.
 */

@Entity(tableName = "address_info")
public class AddressInfo {

    @PrimaryKey(autoGenerate = true)
    public long id;
    public String road;
    public String city;

    @Override
    public String toString() {
        return "AddressInfo{" +
                "road='" + road + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public static AddressInfo createNewInfo(){
        AddressInfo info = new AddressInfo();
        info.city = RandomUtil.getRandomCity();
        info.road = RandomUtil.getRoad();
        return info;
    }
}
