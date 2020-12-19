package me.ezerror.util.sqlbuilder;

import me.ezerror.util.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SqlCondition {
    private final List<condition> conditionList = new ArrayList<>();

    public static final String SPILT = "_SPILT_";

    public SqlCondition eq(String field, Object value) {
        conditionList.add(new condition(field, value, "="));
        return this;
    }

    public SqlCondition eq(String field, Object value, String conditionMode) {
        conditionList.add(new condition(field, value, "=", conditionMode));
        return this;
    }

    public SqlCondition nq(String field, Object value) {
        conditionList.add(new condition(field, value, "!=")); return this;
    }

    public SqlCondition nq(String field, Object value, String conditionMode) {
        conditionList.add(new condition(field, value, "!=", conditionMode));
        return this;
    }

    public List<condition> getConditionList() {
        return this.conditionList;
    }

    public SqlCondition in(String field, String value) {
        conditionList.add(new condition(field, "(" + value + ")", "in"));
        return this;
    }

    public SqlCondition in(String field, SqlBuilder sqlBuilder) {
        String value = sqlBuilder.toCompleteSql();
        conditionList.add(new condition(field, "(" + value + ")", "in"));
        return this;
    }

    public SqlCondition or(String field, String value) {
        conditionList.add(new condition(field, value, "or"));
        return this;
    }

    static class condition {
        String conditionMode = "and";
        String field;
        String value;
        String operate;

        public condition(String field, Object value, String Operate, String conditionMode) {
            this.conditionMode = conditionMode;
            this.field = field;
            this.operate = Operate;
            this.value = parseSqlFiledValue(value);
        }

        public condition(String field, Object value, String Operate) {
            this.field = field;
            this.value = parseSqlFiledValue(value);
            this.operate = Operate;
        }

        //TODO:..
        private String parseSqlFiledValue(Object value) {
            if (value instanceof String) {
                return value.toString();
            }
            if (value instanceof Integer) {
                return String.valueOf(value);
            }
            if (value instanceof Date) {
                return DateUtil.parseDateToString((Date) value, DateUtil.DATE_TIME_FORMAT).toString();
            }
            return value.toString();
        }

        public String getPatchSql() {
            SqlGenerator sql = new SqlGenerator();
            sql.append(conditionMode).append(field).append(operate);
            if (operate.equals("in")) {
                sql.append(value);
            }
            else {
                sql.append("'" + value + "'");
            }
            return sql.toString();
        }

        public String getNoConditionModePatchSql() {
            SqlGenerator sql = new SqlGenerator();
            sql.append(field).append(operate);
            if (operate.equals("in")) {
                sql.append(value);
            }
            else {
                sql.append("'" + value + "'");
            }
            return sql.toString();
        }
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
}
