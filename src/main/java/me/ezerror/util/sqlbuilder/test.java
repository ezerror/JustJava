package me.ezerror.util.sqlbuilder;

import me.ezerror.util.DateUtil;

import java.util.Date;

public class test {
    public static void main(String[] args) {
        SqlBuilder builder = new SqlBuilder(TestObj.class);
        builder.select("rowguid");
        builder.setInnerJoin("tableb b","a.rowguid","b.rowguid");
        builder.setLeftJoin("tableb c","a.rowguid","c.rowguid");
        builder.setRightJoin("tableb d","a.rowguid","d.rowguid");
        System.out.println(builder.toCompleteSql());
    }
}
