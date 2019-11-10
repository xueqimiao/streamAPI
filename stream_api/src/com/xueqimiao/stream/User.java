package com.xueqimiao.stream;

/**
 * @version v1.0
 * @Author: xueqimiao
 * @Date: 2019/11/8 16:40
 */
public class User {

    //编号
    private Integer id;

    //姓名
    private String name;

    //年龄
    private Integer age;

    //生日
    private String birthday;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public User(Integer id, String name, Integer age, String birthday) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }
}
