package com.goinghugh.dbmock.model;

import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 表结构
 *
 * @author yongxu wang
 * @date 2019-05-06 07:12
 **/
public class TableStructure {

    private static Pattern pattern = Pattern.compile("\\((.*?)\\)");

    private String schemaName;

    private String tableName;

    private List<Column> columns;

    private String insertSql;


    public TableStructure(String schemaName, String tableName, List<Column> columns) {
        this.schemaName = schemaName;
        this.tableName = tableName;
        this.columns = columns;
        this.insertSql = getCreateSql();
    }

    public int getPlaceholder() {
        int count = 0;
        for (Column col : columns) {
            if (!col.getAutoincrement()) {
                count++;
            }
        }
        return count;
    }

    public int getColumnIndex(Column column) {
        String createSql = getInsertSql();
        Matcher matcher = pattern.matcher(createSql);
        if (matcher.find()) {
            String col = matcher.group(1);
            String[] strings = col.split(",");
            for (int i = 0; i < strings.length; i++) {
                if (column.getColumnName().equals(strings[i])) {
                    return i + 1;
                }
            }
        }
        throw new IllegalArgumentException("列有误");
    }

    private String getCreateSql() {
        // insert into joke (gid,name)value(0,"joker");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(schemaName);
        sb.append(".");
        sb.append(tableName);
        sb.append(" (");
        columns.stream()
                .filter(column -> !column.getAutoincrement())
                .forEach(column -> sb.append(column.getColumnName()).append(","));

        sb.deleteCharAt(sb.length() - 1);
        sb.append(") value (");
        columns.stream()
                .filter(column -> !column.getAutoincrement())
                .forEach(column -> sb.append("?,"));
        sb.deleteCharAt(sb.length() - 1);
        sb.append(");");
        return sb.toString();
    }

    public String getInsertSql() {
        return insertSql;
    }

    public void setInsertSql(String insertSql) {
        this.insertSql = insertSql;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TableStructure.class.getSimpleName() + "[", "]")
                .add("schemaName='" + schemaName + "'")
                .add("tableName='" + tableName + "'")
                .add("columns=" + columns)
                .toString();
    }
}
