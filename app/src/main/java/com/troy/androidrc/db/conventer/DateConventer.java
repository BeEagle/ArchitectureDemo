package com.troy.androidrc.db.conventer;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by Troy on 2018/6/11.
 */

public class DateConventer {

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

}
