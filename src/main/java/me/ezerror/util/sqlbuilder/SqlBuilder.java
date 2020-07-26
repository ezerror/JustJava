package me.ezerror.util.sqlbuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SqlBuilder {
    private final String tableName;
    private final String[] id;
    private String selectField = "*";
    private final String countField;
    private List<Join> joinList;
    private String limit = "";


    public SqlBuilder(Class<TestObj> entity) {
        Entity annotation = entity.getAnnotation(Entity.class);
        this.tableName = annotation.table();
        this.id = annotation.id();
        countField = this.id.length > 0 ? this.id[0] : "1";
    }

    public SqlBuilder select(String selectField) {
        this.selectField = selectField;
        return this;
    }

    public String toCompleteSql() {
        SqlGenerator sql = new SqlGenerator();
        sql.append("select").append(this.selectField);
        sql.append("from").append(this.tableName);
        if (this.joinList != null && !this.joinList.isEmpty()) {
            sql.append("a");
            for (Join join : joinList) {
                sql.append(join.joinMode);
                sql.append(join.joinTable);
                sql.append("on").append(join.columnA).append("=").append(join.columnB);
            }
        }
        sql.append(limit);
        return sql.toString();
    }

    public String toCountSql() {
        StringBuilder sql = new StringBuilder();
        sql.append("select ").append("count(").append(countField).append(")");
        sql.append(" from ").append(this.tableName);
        return sql.toString();
    }

    public void setInnerJoin(String joinTable, String columnA, String columnB) {
        setJoin(joinTable, columnA, columnB, "inner join");
    }

    public void setLeftJoin(String joinTable, String columnA, String columnB) {
        setJoin(joinTable, columnA, columnB, "left join");
    }

    public void setRightJoin(String joinTable, String columnA, String columnB) {
        setJoin(joinTable, columnA, columnB, "right join");
    }

    private void setJoin(String joinTable, String columnA, String columnB, String joinMode) {
        if (joinList == null) {
            joinList = new ArrayList<>();
        }
        joinList.add(new Join(joinMode, joinTable, columnA, columnB));
    }

    public void limit(int limitNum) {
        this.limit = "limit " + limitNum;
    }

    public void limit(int first, int limitNum) {
        this.limit = "limit " + first + "," + limitNum;
    }

    private static class SqlGenerator {
        StringBuilder sql = new StringBuilder();

        private SqlGenerator append(String s) {
            this.sql.append(s).append(" ");
            return this;
        }

        public String toString() {
            return this.sql.toString();
        }
    }

    private static class Join {
        String joinMode;
        String joinTable;
        String columnA;
        String columnB;

        Join(String joinMode, String joinTable, String columnA, String columnB) {
            this.joinMode = joinMode;
            this.joinTable = joinTable;
            this.columnA = columnA;
            this.columnB = columnB;
        }
    }
}
