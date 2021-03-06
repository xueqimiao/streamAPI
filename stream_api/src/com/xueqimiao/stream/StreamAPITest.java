package com.xueqimiao.stream;

import org.junit.Test;

import javax.swing.plaf.synth.SynthSpinnerUI;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @version v1.0
 * @Author: xueqimiao
 * @Date: 2019/11/8 16:36
 */
public class StreamAPITest {

    //1.取出自身重复的数据
    @Test
    public void test01() {
        List<String> list = Arrays.asList(
                "g", "k", "f", "k", "g", "b"
        );
        List<String> li = list.stream().filter(li1 -> list.stream().filter((li2 -> li2.equals(li1))).count() > 1l)
                .collect(toList());
        System.out.println(li);
    }

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

    //2.取出前2个user01s的姓名
    @Test
    public void test02() {
        List<String> nameList = users01.stream().map(User::getName).limit(2).collect(toList());
        System.out.println(nameList);
        Set<String> nameSet = users01.stream().map(User::getName).collect(Collectors.toSet());
        System.out.println(nameSet);
        HashSet<String> nameHash = users01.stream().map(User::getName).collect(Collectors.toCollection(HashSet::new));
        System.out.println(nameHash);
    }

    //3.取出users01中age一样的，并算出有几项
    @Test
    public void test03() {
        List<User> userList = users01.stream().filter(user1 -> users01.stream().filter((user2 -> user2.getAge().equals(user1.getAge()))).count() > 1l)
                .collect(toList());

        userList.stream().forEach((user) -> {
            System.out.println(user.getName() + ":" + user.getAge());
        });
        Map<Object, Object> str = new HashMap<>();
        for (User user : userList) {
            List<User> collect = userList.stream().filter(d -> d.getAge().equals(user.getAge())).collect(toList());
            str.put(user.getAge(), collect.size());
        }
        for (Object key : str.keySet()) {
            String value = str.get(key).toString();//
            System.out.println("key:" + key + " value:" + value);
        }
    }

    /**
     * allMatch——检查是否匹配所有元素
     * anyMatch——检查是否至少匹配一个元素
     * noneMatch——检查是否没有匹配的元素
     */
    //4.取出users01中的Id在users02中没有的user
    @Test
    public void test04() {
        //取出所有users02的id
        List<Integer> user02Ids = users02.stream().map(User::getId).collect(toList());
        System.out.println(user02Ids);
        List<User> userList = users01.stream().filter(user1 -> !user02Ids.stream().anyMatch(user02Id -> user1.getId().equals(user02Id))).collect(toList());
        userList.stream().forEach((user) -> {
            System.out.println(user.getName() + ":" + user.getAge());
        });
    }

    //5.累加users01的所有年龄
    @Test
    public void test05() {
        int sum = users01.stream().mapToInt(User::getAge).sum();
        System.out.println(sum);

        Optional<Integer> op = users01.stream()
                .map(User::getAge)
                .reduce(Integer::sum);
        System.out.println(op.get());
    }

    //6.users01按照birthday排序
    @Test
    public void test06() {

        //根据年龄逆序
        List<User> userList = users01.stream().sorted(Comparator.comparing(User::getBirthday).reversed()).collect(toList());
        userList.stream().forEach((user) -> {
            System.out.println(user.getName() + ":" + user.getAge() + ":" + user.getBirthday());
        });
        System.out.println("--------------------------");
        //根据年龄自然顺序
        List<User> userList1 = userList.stream().sorted(Comparator.comparing(User::getBirthday)).collect(toList());
        userList1.stream().forEach((user) -> {
            System.out.println(user.getName() + ":" + user.getAge() + ":" + user.getBirthday());
        });
    }

    //7.去掉users01name重复的只保留一个
    @Test
    public void test07() {
        List<User> userList = users01.stream().collect(
                Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getName()))),
                        ArrayList::new));
        userList.stream().forEach((user) -> {
            System.out.println(user.getName() + ":" + user.getAge() + ":" + user.getBirthday());
        });
    }

    /**
     * findFirst——返回第一个元素
     */
    //8.找到users01中age最小的user
    @Test
    public void test08() {
        //age最大的数
        Optional<Integer> op = users01.stream()
                .map(User::getAge)
                .max(Double::compare);
        System.out.println(op.get());

        //age最小的user
        Optional<User> user = users01.stream()
                .sorted((u1, u2) -> Double.compare(u1.getAge(), u2.getAge())).findFirst();
        System.out.println(user.get().getName());
    }

    /**
     * findAny——返回当前流中的任意元素
     */
    //9.找到users01中任意一个age是23的user
    @Test
    public void test09() {
        Optional<User> user = users01.parallelStream()
                .filter((u) -> u.getAge().equals(23))
                .findAny();
        System.out.println(user.get().getName());
    }

    //10.找到users01中age是23的个数
    @Test
    public void test10() {
        long count = users01.stream()
                .filter((u) -> u.getAge().equals(23))
                .count();
        System.out.println(count);
    }

    //11.使用归约统计user的最高、最低、平均、总和age
    @Test
    public void test11() {
        Optional<Integer> max = users01.stream()
                .map(User::getAge)
                .collect(Collectors.maxBy(Integer::compare));
        System.out.println(max.get());

        Optional<User> op = users01.stream()
                .collect(Collectors.minBy((u1, u2) -> Double.compare(u1.getAge(), u2.getAge())));

        System.out.println(op.get().getAge());

        Double avg = users01.stream()
                .collect(Collectors.averagingDouble(User::getAge));

        System.out.println(avg);

        Double sum = users01.stream()
                .collect(Collectors.summingDouble(User::getAge));
        System.out.println(sum);

        Optional<Integer> sum2 = users01.stream()
                .map(User::getAge)
                .collect(Collectors.reducing(Integer::sum));

        System.out.println(sum2.get());

        DoubleSummaryStatistics dss = users01.stream()
                .collect(Collectors.summarizingDouble(User::getAge));

        System.out.println(dss.getMax());
    }

    /**
     * 归约
     * reduce(T identity, BinaryOperator) / reduce(BinaryOperator) ——可以将流中元素反复结合起来，得到一个值。
     */
    //12.累加数字
    @Test
    public void test12() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer sum = list.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(sum);
    }

    List<Student> stuList = Arrays.asList(
            new Student(1, "张三", 1),
            new Student(2, "李四", 2),
            new Student(3, "李飞", 2)
    );

    List<Grade> gradeList = Arrays.asList(
            new Grade(1, "大一"),
            new Grade(2, "大二")
    );

    //13.将Student信息注入到相应的Grade里面的studentList中
    @Test
    public void test13() {
        gradeList.stream().forEach((grade) -> {
            List<Student> students = stuList.stream().filter((stu) -> stu.getGradeId().equals(grade.getGradeId())).collect(toList());
            grade.setStudentList(students);
        });
        gradeList.stream().forEach(grade -> {
            System.out.println(grade.getGradeName() + "---");
            grade.getStudentList().stream().forEach(stu -> {
                System.out.println(stu.getStuName());
            });
        });
    }

    //14.字符串数字集合与整数集合互转
    @Test
    public void test14() {
        List<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(3);
        intList.add(4);
        List<String> intToStrList = intList.stream().map(String::valueOf).collect(toList());
        intToStrList.stream().forEach(str -> {
            System.out.println(str);
        });

        List<String> stringList = new ArrayList<>();
        stringList.add("10");
        stringList.add("20");
        stringList.add("30");
        List<String> stringToIntList = stringList.stream().map(String::valueOf).collect(toList());
        stringToIntList.stream().forEach(num -> {
            System.out.println(num);
        });
    }

    List<String> list1 = Arrays.asList(
            "张三", "李四", "李刚", "隋通"
    );

    List<String> list2 = Arrays.asList(
            "隋凯", "李通", "张三", "李刚"
    );

    //15.取list1与list2的交集
    @Test
    public void test15() {
        List<String> intersection = list1.stream().filter(item -> list2.contains(item)).collect(toList());
        System.out.println("---得到交集---");
        intersection.parallelStream().forEach(System.out::println);
    }

    //16.取list1与list2的差集
    @Test
    public void test16() {
        List<String> reduce1 = list1.stream().filter(item -> !list2.contains(item)).collect(toList());
        System.out.println("---得到差集 reduce1 (list1 - list2)---");
        reduce1.parallelStream().forEach(System.out::println);
    }

    //17.取list1与list2的差集
    @Test
    public void test17() {
        List<String> reduce2 = list2.stream().filter(item -> !list1.contains(item)).collect(toList());
        System.out.println("---得到差集 reduce2 (list2 - list1)---");
        reduce2.parallelStream().forEach(System.out::println);
    }

    //18.取list1和list2的并集
    @Test
    public void test18() {
        List<String> listAll = list1.parallelStream().collect(toList());
        List<String> listAll2 = list2.parallelStream().collect(toList());
        listAll.addAll(listAll2);
        System.out.println("---得到并集 listAll---");
        listAll.parallelStream().forEach(System.out::println);
        // 去重并集
        List<String> listAllDistinct = listAll.stream().distinct().collect(toList());
        System.out.println("---得到去重并集 listAllDistinct---");
        listAllDistinct.parallelStream().forEach(System.out::println);
        System.out.println("---原来的List1---");
        list1.parallelStream().forEach(System.out::println);
        System.out.println("---原来的List2---");
        list2.parallelStream().forEach(System.out::println);
    }

    List<String> list = Arrays.asList(
            "b", "g", "d", "d", "t", "j", "d", "a", "c", "f", "a", "b", "s", "w", "w", "b"
    );

    //19.统计集合重复元素出现次数，并且去重返回hashmap
    @Test
    public void test19() {
        Map<String, Long> map = list.stream().
                collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(map);
        //由于hashmap无序，所以在排序放入LinkedHashMap里(key升序)
        Map<String, Long> sortMap = new LinkedHashMap<>();
        map.entrySet().stream().sorted(Map.Entry.comparingByKey()).
                forEachOrdered(e -> sortMap.put(e.getKey(), e.getValue()));
        System.out.println(sortMap);
        //获取排序后map的key集合
        List<String> keys = new LinkedList<>();
        sortMap.entrySet().stream().forEachOrdered(e -> keys.add(e.getKey()));
        System.out.println(keys);
        //获取排序后map的value集合
        List<Long> values = new LinkedList<>();
        sortMap.entrySet().stream().forEachOrdered(e -> values.add(e.getValue()));
        System.out.println(values);
    }

    //20.模拟数据库模糊查询users01
    @Test
    public void test20(){
        users01.stream().forEach(u->{
            System.out.println(u.getAge());
        });
        System.out.println("----------------------");
        users01 = users01.stream().filter(vo -> {
            return  vo.getName().contains("三")||vo.getBirthday().contains("2018");
        }).collect(toList());
        users01.stream().forEach(u->{
            System.out.println(u.getName()+":"+u.getBirthday());
        });
    }

    //21.stream循环拿到下标
    @Test
    public void test21(){
        Stream.iterate(0, i -> i + 1).limit(users01.size()).forEach(index -> {
           System.out.println(index);
        });
    }

    @Test
    public void test22(){
        Map<Integer,List<User>> userMap = new HashMap<>();
        List<User> userList = null;
        for (int i = 0; i < users02.size(); i++) {
            User user = users02.get(i);
            if(userMap.get(user.getAge())==null){
                userList = new ArrayList<>();
            }
            userList.add(user);
            userMap.put(user.getAge(),userList);
        }
        for(Integer key : userMap.keySet()){
           System.out.println("key:"+key+"\n"+"value:"+userMap.get(key));
        }
    }

    @Test
    public void test23(){
        Map<Integer, List<User>> listMap = users02.stream().collect(Collectors.groupingBy(User::getAge));
        listMap.keySet().forEach(key->{
            System.out.println(key);
            listMap.get(key).forEach((user)->System.out.println(user));
        });
        for(Integer key : listMap.keySet()){
            System.out.println("key:"+key+"\n"+"value:"+listMap.get(key));
        }
    }
}
