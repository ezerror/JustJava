package me.ezerror.util.sqlbuilder;

import me.ezerror.util.DateUtil;

import java.util.Date;

public class test {
    public static void main(String[] args) {
        String s = new SqlBuilder.Builder(TestObj.class).select("rowguid").build().toCompleteSql();
        String countSql = new SqlBuilder.Builder(TestObj.class).build().toCountSql();

        System.out.println(DateUtil.parseDateToString(new Date()));


        System.out.println(s);
    }
}
