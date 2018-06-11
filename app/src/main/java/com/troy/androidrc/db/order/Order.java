package com.troy.androidrc.db.order;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.troy.androidrc.utils.RandomUtil;

/**
 * Created by Troy on 2018/6/9.
 */

@Entity(tableName = "orders")
public class Order {

    @PrimaryKey
    @ColumnInfo(name = "order_id")
    public long orderId;

    @ColumnInfo(name = "address")
    public String address;

    @ColumnInfo(name = "owner_name")
    public String ownerName;

    @ColumnInfo(name = "owner_phone")
    public String ownerPhone;

    //  指示 Room 需要忽略的字段或方法
    @Ignore
    public String ignoreText;

    @Embedded
    public OwnerAddress ownerAddress;

//    @Embedded
//    //  @ColumnInfo(name = "address_detail")
//    public AddressInfo addressDetail;

    public static Order createNewOrder(){
        Order order = new Order();
        order.orderId = RandomUtil.getRandomId();
        order.address = RandomUtil.getRoad();
        order.ownerName = RandomUtil.getChineseName();
        order.ownerPhone = RandomUtil.getRandomPhone();
        order.ownerAddress = OwnerAddress.createNewAddress();
        return order;
    }

    static class OwnerAddress {

        public String street;
        public String state;
        public String city;

        @ColumnInfo(name = "post_code")
        public int postCode;

        public static OwnerAddress createNewAddress(){
            OwnerAddress v2 = new OwnerAddress();
            v2.city = RandomUtil.getRandomCity();
            v2.postCode = RandomUtil.getNum(100000, 999999);
            v2.state = "finish";
            return v2;
        }

        @Override
        public String toString() {
            return "OwnerAddress{" +
                    "street='" + street + '\'' +
                    ", state='" + state + '\'' +
                    ", city='" + city + '\'' +
                    ", postCode=" + postCode +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", address='" + address + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", ownerPhone='" + ownerPhone + '\'' +
                ", ignoreText='" + ignoreText + '\'' +
                ", ownerAddress=" + ownerAddress.toString() +
                '}';
    }
}
