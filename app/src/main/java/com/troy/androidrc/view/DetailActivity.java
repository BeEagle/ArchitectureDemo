package com.troy.androidrc.view;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.troy.androidrc.R;
import com.troy.androidrc.TroyApplication;
import com.troy.androidrc.db.AppDatabase;
import com.troy.androidrc.db.DbManager;
import com.troy.androidrc.db.order.Order;
import com.troy.androidrc.db.order.OrderDao;
import com.troy.androidrc.lifecycle.ActivityLifeObserver;
import com.troy.androidrc.lifecycle.LocationLifeObserver;
import com.troy.androidrc.model.User;
import com.troy.androidrc.model.UserViewModel;
import com.troy.androidrc.utils.LogUtil;
import com.troy.androidrc.utils.RandomUtil;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DetailActivity extends AppCompatActivity implements LifecycleOwner{

    private static String TAG = DetailActivity.class.getSimpleName();
    private LifecycleRegistry mLifecycleRegistry;

    private UserViewModel mUserViewModel;
    private Button mButtonOrderNumber, mButtonUser;
    private Button mButtonOrderDelete, mButtonOrderAdd, mButtonOrderUpdate;
    private ActivityLifeObserver mActivityLifeObserver;
    private TextView mTvUser;
    private TextView mTvOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        // 初始化
        mLifecycleRegistry = new LifecycleRegistry(this);
        mActivityLifeObserver = new ActivityLifeObserver();

        //  注册观察者
        mLifecycleRegistry.addObserver(mActivityLifeObserver);
        mLifecycleRegistry.addObserver(new LocationLifeObserver());

        //  移除观察者
        mLifecycleRegistry.removeObserver(mActivityLifeObserver);
        initViews();
        initData();
    }

    @Override
    public Lifecycle getLifecycle() {
        return mLifecycleRegistry;
    }

    private void initViews(){
        mButtonOrderNumber = findViewById(R.id.btn_order_number);
        mButtonOrderDelete = findViewById(R.id.btn_order_delete);
        mButtonOrderAdd = findViewById(R.id.btn_order_add);
        mButtonOrderUpdate = findViewById(R.id.btn_order_update);
        mButtonUser = findViewById(R.id.btn_user);
        mTvUser = findViewById(R.id.tv_user);
        mTvOrder = findViewById(R.id.tv_order);

        mButtonOrderAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  room
                addOrderDataToDb();
            }
        });

        mButtonOrderDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                deleteLastOrderFromDb();
            }
        });

        mButtonOrderUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateOrderFromDb();
            }
        });

        mButtonOrderNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Order> orderList = queryOrderFromDb();
                if(orderList != null && orderList.size() > 0){
                    StringBuilder builder = new StringBuilder();
                    for (Order order : orderList){
                        if(order != null){
                            builder.append(order.toString());
                            builder.append("\n\n");
                        }
                    }
                    if(!TextUtils.isEmpty(builder.toString())){
                        mTvOrder.setText(builder.toString());
                    }
                }
            }
        });

        mButtonUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUserViewModel != null && mUserViewModel.getData() != null){
                    mUserViewModel.changeData();
                }
            }
        });
    }

    private void initData(){

        //  view model
        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        mUserViewModel.getData().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                if(user != null){
                    LogUtil.i(TAG, user.toString());
                    mTvUser.setText(user.toString());
                }
            }
        });
    }

    private void addOrderDataToDb(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase db = DbManager.getDbInstance();
                OrderDao orderDao = db.getOrderDao();
                Order order = Order.createNewOrder();
                orderDao.insertAll(order);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(TroyApplication.getInstance(), "添加数据 success ", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }

    private void deleteLastOrderFromDb(){
        List<Order> orderList = queryOrderFromDb();
        if(orderList != null && orderList.size() > 0){
            AppDatabase db = DbManager.getDbInstance();
            OrderDao orderDao = db.getOrderDao();
            orderDao.deleteOrder(orderList.get(orderList.size() - 1));
            final long orderId = orderList.get(orderList.size() - 1).orderId;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(TroyApplication.getInstance(), "删除订单： " + orderId + " success ", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void updateOrderFromDb(){
        List<Order> orderList = queryOrderFromDb();
        if(orderList != null && orderList.size() > 0){
            AppDatabase db = DbManager.getDbInstance();
            OrderDao orderDao = db.getOrderDao();
            Order order = orderList.get(orderList.size() - 1);
            order.ownerName = "update - " + RandomUtil.getChineseName();
            orderDao.updateOrder(order);
        }
    }

    private List<Order> queryOrderFromDb(){
        AppDatabase db = DbManager.getDbInstance();
        OrderDao orderDao = db.getOrderDao();
        return orderDao.loadAllOrders();
    }

    private Flowable<Order> queryOrderV2(){
        AppDatabase db = DbManager.getDbInstance();
        OrderDao orderDao = db.getOrderDao();
        return orderDao.queryOrderByIdV2(10001);
    }

    private Maybe<Order> queryOrderV3(){
        AppDatabase db = DbManager.getDbInstance();
        OrderDao orderDao = db.getOrderDao();
        return orderDao.queryOrderByIdV3(10001);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Maybe<Order> orderMaybe = queryOrderV3();
        orderMaybe.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Consumer<Order>() {
                    @Override
                    public void accept(@NonNull Order order) throws Exception {

                    }
                });

        Flowable<Order> orderFlowable =  queryOrderV2();
        orderFlowable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<Order>() {
                    @Override
                    public void onSubscribe(Subscription s) {

                    }

                    @Override
                    public void onNext(Order order) {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void showToast(){

    }
}
