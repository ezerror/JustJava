package me.ezerror.util.sqlbuilder;

public class test {
    public static void main(String[] args) {
        SqlBuilder builder = new SqlBuilder("tablename");
        builder.select("name,count(id) count");
        builder.groupBy("name");
        builder.orderBy("count", "desc");
        SqlCondition sqlCondition = new SqlCondition();
        sqlCondition.eq("a", "b");
        builder.setCondition(sqlCondition);
        builder.setOrCondition(new SqlCondition().eq("a", "b").eq("a", "b","or"));
        System.out.println(builder.toCompleteSql());
    }
}
