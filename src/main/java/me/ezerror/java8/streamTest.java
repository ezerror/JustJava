package me.ezerror.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class streamTest {

    public static void main(String[] args) {
        testMap();
    }

    private static void testMap() {

        List<HashMap<String,String>> mapList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            HashMap<String,String> map = new HashMap<>();
            map.put("key1", String.valueOf(i));
            map.put("key2", String.valueOf(i));
            map.put("key3", String.valueOf(i));
            map.put("key4", String.valueOf(i));
            mapList.add(map);
        }

        long start = System.currentTimeMillis();

        List<HashMap<String, String>> collect = mapList.stream().map((map) -> {
            HashMap<String, String> temp = new HashMap<>();
            temp.put("key1", map.get("key1"));
            temp.put("key2", map.get("key2"));
            return temp;
        }).collect(Collectors.toList());
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        //System.out.println(collect);
    }


}
