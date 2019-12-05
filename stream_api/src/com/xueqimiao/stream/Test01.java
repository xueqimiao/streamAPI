package com.xueqimiao.stream;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Test01 {


    @Test
    public void test01() {

    }

    public static int multiply(int num) {
        if (num < 0) {
            System.out.println("请输入大于0的数！");
            return -1;
        } else if (num == 0 || num == 1) {
            return 1;
        } else {
            return multiply(num - 1) * num;
        }
    }

    public static void main(String[] args) {
        System.out.println(multiply(10));
    }

}
