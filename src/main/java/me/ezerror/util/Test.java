package me.ezerror.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        // 几个一组
        int groupSize =10;
        // 切成几组
        int limit = (list.size() + groupSize - 1) / groupSize;
        List<List<Integer>> splitList = Stream.iterate(0, n -> n + 1).limit(limit).parallel().map(a -> list.stream().skip(a * groupSize).limit(groupSize).parallel().collect(Collectors.toList())).collect(Collectors.toList());
        System.out.println(splitList);
    }
}

class People {
    String name;
    String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return name + " " + age;
    }
}
