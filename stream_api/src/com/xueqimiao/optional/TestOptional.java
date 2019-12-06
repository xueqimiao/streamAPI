package com.xueqimiao.optional;

import com.xueqimiao.stream.User;
import org.junit.Test;

import java.util.Optional;

/*
 * 一、Optional 容器类：用于尽量避免空指针异常
 * 	Optional.of(T t) : 创建一个 Optional 实例
 * 	Optional.empty() : 创建一个空的 Optional 实例
 * 	Optional.ofNullable(T t):若 t 不为 null,创建 Optional 实例,否则创建空实例
 * 	isPresent() : 判断是否包含值
 * 	orElse(T t) :  如果调用对象包含值，返回该值，否则返回t
 * 	orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回 s 获取的值
 * 	map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()
 * 	flatMap(Function mapper):与 map 类似，要求返回值必须是Optional
 */
public class TestOptional {

    @Test
    public void test1(){
        Optional<User> op = Optional.of(new User());
        User user = op.get();
        System.out.println(user.getName());
    }

    @Test
    public void test2(){
		/*Optional<User> op = Optional.ofNullable(null);
		System.out.println(op.get());*/

		/*Optional<User> op = Optional.empty();
		System.out.println(op.get());*/
    }

    @Test
    public void test3(){
        Optional<User> op = Optional.ofNullable(new User());

        if(op.isPresent()){
            System.out.println(op.get());
        }

        User u1 = op.orElse(new User("张三"));
        System.out.println(u1);

        User u2 = op.orElseGet(() -> new User());
        System.out.println(u2);
    }

    @Test
    public void test4(){
        Optional<User> op = Optional.of(new User(101, "张三", 18, "2012-12-12"));

        Optional<String> op2 = op.map(User::getName);
        System.out.println(op2.get());

        Optional<String> op3 = op.flatMap((e) -> Optional.of(e.getName()));
        System.out.println(op3.get());
    }

}
