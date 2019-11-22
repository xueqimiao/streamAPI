package com.xueqimiao.stream;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Test01 {

    //模拟数据库查询出的user集合01
    List<User> users01 = Arrays.asList(
            new User(1, "张三", 26, "2018-12-31"),
            new User(2, "李四", 23, "2017-05-06"),
            new User(3, "小安", 23, "2018-01-25"),
            new User(4, "二狗", 26, "1995-09-03"),
            new User(5, "三哈", 23, "1998-11-13"),
            new User(6, "三哈", 27, "1998-11-13")
    );

    //模拟数据库查询出的user集合02
    List<User> users02 = Arrays.asList(
            new User(3, "王五", 15, "1995-05-27"),
            new User(4, "李明", 27, "1949-10-01"),
            new User(5, "小刘", 26, "2019-09-06"),
            new User(6, "小康", 28, "2018-07-31"),
            new User(7, "赵雷", 13, "2015-05-22")
    );

    @Test
    public void test01() {
        /*List<User> userList = users01.stream().filter(user1 -> users01.stream().filter((user2 -> user2.getAge().equals(user1.getAge()))).count() > 0l)
                .collect(Collectors.toList());

        userList.stream().forEach((user) -> {
            System.out.println(user.getName() + ":" + user.getAge());
        });
        Map<Object, Object> str = new HashMap<>();
        for (User user : userList) {

            List<User> collect = userList.stream().filter(d -> d.getAge().equals(user.getAge())).collect(Collectors.toList());
            str.put(user.getAge(), collect.size());
        }
        for (Object key : str.keySet()) {
            String value = str.get(key).toString();//
            System.out.println("key:" + key + " value:" + value);
        }*/
    }


}
