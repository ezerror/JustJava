package me.ezerror.util.sqlbuilder;

public class test {
    public static void main(String[] args) {
        SqlBuilder builder = new SqlBuilder("tablename");
        builder.select("name,count(id) count");
        builder.groupBy("name");
        builder.orderBy("count", "desc");
        System.out.println(builder.toCompleteSql());
    }
}
