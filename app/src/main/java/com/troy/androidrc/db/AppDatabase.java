package com.troy.androidrc.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.troy.androidrc.db.address.AddressInfo;
import com.troy.androidrc.db.order.Order;
import com.troy.androidrc.db.order.OrderDao;

/**
 * Created by Troy on 2018/6/9.
 */

@Database(entities = {Order.class, AddressInfo.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase{

   public abstract OrderDao getOrderDao();


}
