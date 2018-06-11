package com.troy.androidrc.utils;

import com.troy.androidrc.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Troy on 2018/6/9.
 */

public class RandomUtil {

    public static String base = StringUtil.getString(R.string.random_base);
    private static String firstName = StringUtil.getString(R.string.random_first_name);
    private static String girl = StringUtil.getString(R.string.random_girl);
    private static String boy = StringUtil.getString(R.string.random_boy);
    private static String[] road = StringUtil.getString(R.string.random_road).split(",");
    private static String[] city = StringUtil.getString(R.string.random_city).split(",");
    private static final String[] email_suffix = StringUtil.getString(R.string.random_email_suffix).split(",");

    public static int getNum(int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }

    /**
     * 返回Email
     *
     * @param lMin 最小长度
     * @param lMax 最大长度
     * @return
     */
    public static String getEmail(int lMin, int lMax) {
        int length = getNum(lMin, lMax);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = (int) (Math.random() * base.length());
            sb.append(base.charAt(number));
        }
        sb.append(email_suffix[(int) (Math.random() * email_suffix.length)]);
        return sb.toString();
    }

    /**
     * 返回手机号码
     */
    private static String[] telFirst = "134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");

    public static String getRandomPhone() {
        int index = getNum(0, telFirst.length - 1);
        String first = telFirst[index];
        String second = String.valueOf(getNum(1, 888) + 10000).substring(1);
        String third = String.valueOf(getNum(1, 9100) + 10000).substring(1);
        return first + second + third;
    }

    public static String getRandomCity(){
        int index = getNum(0, city.length - 1);
        String first = city[index];
        return first;
    }

    /**
     * 返回中文姓名
     */
    private static String name_sex = "";

    public static String getChineseName() {
        int index = getNum(0, firstName.length() - 1);
        String first = firstName.substring(index, index + 1);
        int sex = getNum(0, 1);
        String str = boy;
        int length = boy.length();
        if (sex == 0) {
            str = girl;
            length = girl.length();
            name_sex = "女";
        } else {
            name_sex = "男";
        }
        index = getNum(0, length - 1);
        String second = str.substring(index, index + 1);
        int hasThird = getNum(0, 1);
        String third = "";
        if (hasThird == 1) {
            index = getNum(0, length - 1);
            third = str.substring(index, index + 1);
        }
        return first + second + third;
    }

    /**
     * 返回地址
     *
     * @return
     */
    public static String getRoad() {
        int index = getNum(0, road.length - 1);
        String first = road[index];
        String second = String.valueOf(getNum(11, 150)) + "号";
        String third = "-" + getNum(1, 20) + "-" + getNum(1, 10);
        return first + second + third;
    }

    /**
     * 数据封装
     *
     * @return
     */
    public static Map getAddress() {
        Map map = new HashMap();
        map.put("name", getChineseName());
        map.put("sex", name_sex);
        map.put("road", getRoad());
        map.put("tel", getRandomPhone());
        map.put("email", getEmail(6, 9));
        return map;
    }

    public static void test(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(getAddress());
//          System.out.println(getEmailName(6,9));
        }
    }

    public static String getRandomNumber() {
        return "" + (1000000 + (int) (Math.random() * (9999999 - 1000000)));
    }

    public static String getRandomAddress() {
        return getRoad();
    }

    public static long getRandomId() {
        return (1000000 + (int) (Math.random() * (9999999 - 1000000)));
    }

    public static String getRandomChars() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            builder.append((char) ('a' + Math.random() * ('z' - 'a' + 1)));
        }
        return builder.toString();
    }

}
