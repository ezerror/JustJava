package me.ezerror.util.sqlbuilder;

import me.ezerror.util.sqlbuilder.SqlCondition.condition;

import java.util.*;

public class SqlBuilder {
    private final String tableName;
    private String[] id;
    private String selectField = "*";
    private String countField;
    private List<Join> joinList;
    private String limit = "";
    private LinkedHashMap<String, SqlCondition> conditionMap = new LinkedHashMap<>();

    public SqlBuilder(Class<TestObj> entity) {
        Entity annotation = entity.getAnnotation(Entity.class);
        this.tableName = annotation.table();
        this.id = annotation.id();
        countField = this.id.length > 0 ? this.id[0] : "1";
    }

    public SqlBuilder(String tableName) {
        this.tableName = tableName;
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
        if (conditionMap.size() > 0) {
            SqlGenerator patchSql = new SqlGenerator();
            patchSql.append("where 1=1");
            for (Map.Entry<String, SqlCondition> map : conditionMap.entrySet()) {
                SqlCondition sqlCondition = map.getValue();
                List<condition> conditionList = sqlCondition.getConditionList();
                for (int i = 0; i < conditionList.size(); i++) {
                    if (i == 0) {
                        patchSql.append(map.getKey()).append("( 1=1");
                    }
                    patchSql.append(conditionList.get(i).getPatchSql());
                    if (i == conditionList.size() - 1) {
                        patchSql.append(")");
                    }
                }
            }
            sql.append(patchSql.toString());
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

    public SqlBuilder setInnerJoin(String joinTable, String columnA, String columnB) {
        setJoin(joinTable, columnA, columnB, "inner join");
        return this;
    }

    public SqlBuilder setLeftJoin(String joinTable, String columnA, String columnB) {
        setJoin(joinTable, columnA, columnB, "left join");
        return this;
    }

    public SqlBuilder setRightJoin(String joinTable, String columnA, String columnB) {
        setJoin(joinTable, columnA, columnB, "right join");
        return this;
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

    public void setCondition(SqlCondition sqlCondition) {
        conditionMap.put("and", sqlCondition);
    }

    public void setOrCondition(SqlCondition sqlCondition) {
        conditionMap.put("or", sqlCondition);
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
