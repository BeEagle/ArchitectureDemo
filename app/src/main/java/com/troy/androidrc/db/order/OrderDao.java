package com.troy.androidrc.db.order;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

/**
 * Created by Troy on 2018/6/9.
 */

@Dao
public interface OrderDao {

    @Query("SELECT * FROM orders")
    List<Order> loadAllOrders();

    @Insert
    void insertAll(Order... orders);

    @Query("SELECT * FROM orders WHERE order_id IN (:orderIds)")
    List<Order> queryOrderById(long[] orderIds);

    @Delete
    void deleteOrder(Order... orders);

    @Update
    void updateOrder(Order... orders);

    @Query("SELECT * FROM orders")
    LiveData<List<Order>> loadAllOrderData();

    @Query("SELECT * from orders where order_id = :id LIMIT 1")
    Flowable<Order> queryOrderByIdV2(long id);

    @Query("SELECT * from orders where order_id = :id LIMIT 1")
    Maybe<Order> queryOrderByIdV3(long id);
}
