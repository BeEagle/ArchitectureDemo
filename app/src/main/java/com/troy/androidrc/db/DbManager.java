package com.troy.androidrc.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.support.annotation.NonNull;

import com.troy.androidrc.TroyApplication;
import com.troy.androidrc.utils.LogUtil;

/**
 * Created by Troy on 2018/6/11.
 */

public class DbManager {

    public static String TAG = DbManager.class.getSimpleName();
    private static AppDatabase DB_INSTANCE;

    public static AppDatabase getDbInstance(){
        if(DB_INSTANCE == null){
            buildDb();
        }
        return DB_INSTANCE;
    }

    public static void buildDb(){
        DB_INSTANCE = Room.
                databaseBuilder(TroyApplication.getInstance(), AppDatabase.class, "troy_db")
                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        LogUtil.i(TAG, "onCreate");
                    }

                    @Override
                    public void onOpen(@NonNull SupportSQLiteDatabase db) {
                        super.onOpen(db);
                        LogUtil.i(TAG, "onOpen");
                    }
                })
                .allowMainThreadQueries()
                .build();

    }



}
