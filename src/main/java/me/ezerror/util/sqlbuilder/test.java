package me.ezerror.util.sqlbuilder;


public class test {
    public static void main(String[] args) {
        SqlBuilder builder = new SqlBuilder("aduit_project");
        builder.setInnerJoin("audit_task b", "a.taskguid","b.rowguid");
        builder.setCondition(new SqlCondition().eq("taskname", "一个事项").eq("taskguid", "1237","or"));
        System.out.println(builder.toCompleteSql());
    }
}
