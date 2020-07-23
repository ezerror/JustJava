package me.ezerror.util.sqlbuilder;

public class test {
    public static void main(String[] args) {
        String s = new SqlBuilder.Builder(TestObj.class).build().toCompleteSql();
        String countSql = new SqlBuilder.Builder(TestObj.class).build().toCountSql();
        System.out.println(countSql);
    }
}
