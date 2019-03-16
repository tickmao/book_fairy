package com.book.fairy.lambda;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: yz-he
 * Date: 2018/4/12 22:53
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class LambdaTest {


    @Test
    public void streamForEach() throws Exception {
        List<String> list = Lists.newArrayList();//新建一个List 用的google提供的Guava  package com.google.core.collect;
        list.add("1");
        list.add("2");
        list.add("3");

        list.stream().forEach(string ->{
            System.out.println(string);
        });
    }

    @Test
    public void streamMap() throws Exception {
        List<String> list1 = Lists.newArrayList();
        List<String> list2 = Lists.newArrayList();
        list1.add("1");
        list1.add("2");
        list1.add("3");

        list2 = list1.stream().map(string -> {
            return "stream().map()处理之后：" + string;
        }).collect(Collectors.toList());

        System.out.println("list2 : " + JSON.toJSONString(list2));

        list2.stream().forEach(string -> {
            System.out.println(string);
        });
    }

    @Test
    public void streamFilter() throws Exception {
        List<String> list1 = Lists.newArrayList();
        List<String> list2 = Lists.newArrayList();
        list1.add("1");
        list1.add("1");
        list1.add("2");
        list1.add("3");

        list2 = list1.stream().filter(s -> s != "1").collect(Collectors.toList());
        System.out.println(list2.toString());
    }

    @Test
    public void streamFilter1() throws Exception {
        //初始化list集合
        List<String> list = new ArrayList<String>();
        list.add("测试数据1");
        list.add("测试数据2");
        list.add("测试数据3");
        list.add("测试数据12");

        //使用λ表达式遍历集合
        list.forEach(s -> System.out.println(s));
        System.out.println("list.forEach ==================================== ");

        //结合Predicate使用和过滤条件筛选元素
        Predicate<String> contain1 = n -> n.contains("1");
        Predicate<String> contain2 = n -> n.contains("2");

        //根据条件遍历集合
        list.stream().filter(contain1).forEach(n -> System.out.println(n));
        System.out.println("contain1.forEach ==================================== ");
        list.stream().filter(s -> contain1.test(s)).forEach(s -> System.out.println(s));
        System.out.println("contain1.test ==================================== ");
        list.stream().filter(contain1.and(contain2)).forEach(n -> System.out.println(n));
        System.out.println("contain1.and ==================================== ");
        list.stream().filter(contain1.or(contain2)).forEach(n -> System.out.println(n));
        System.out.println("contain1.or ==================================== ");

        //将过滤后的元素重新放到一个集合中
        List<String> newList = list.stream().filter(contain1.and(contain2)).collect(Collectors.toList());
        newList.forEach(s -> System.out.println(s));
    }
}