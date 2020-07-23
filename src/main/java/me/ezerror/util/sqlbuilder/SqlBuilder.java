package me.ezerror.util.sqlbuilder;

public class SqlBuilder {
    private final String select;
    private final String tableName;
    private final String[] id;
    private final String countField;


    public SqlBuilder(Builder builder) {
        this.select = builder.selectField;
        this.tableName = builder.tableName;
        this.countField = builder.countField;
        this.id = builder.id;
    }


    public static class Builder {
        private String selectField = "*";
        private String countField;
        private final String tableName;
        private final String[] id;

        public Builder(Class<TestObj> entity) {
            Entity annotation = entity.getAnnotation(Entity.class);
            this.tableName = annotation.table();
            this.id = annotation.id();
            countField = this.id.length > 0 ? this.id[0] : "1";
        }

        public Builder select(String selectField) {
            this.selectField = selectField;
            return this;
        }

        public SqlBuilder build() {
            return new SqlBuilder(this);
        }
    }

    public String toCompleteSql() {
        StringBuilder sql = new StringBuilder();
        sql.append(" select ").append(this.select);
        sql.append(" from ").append(this.tableName);
        return sql.toString();
    }

    public String toCountSql() {
        StringBuilder sql = new StringBuilder();
        sql.append(" select ").append("count(").append(countField).append(")");
        sql.append(" from ").append(this.tableName);
        return sql.toString();
    }

}
