package com.goinghugh.dbmock.model;

import com.goinghugh.dbmock.generator.Generator;

import java.util.StringJoiner;

/**
 * db 列
 *
 * @author yongxu wang
 * @date 2019-05-05 20:08
 **/
public class Column {

    private String tableCat;

    private String tableName;

    private String columnName;

    /**  
     * 例如4,对应int类型
     */  
    private String jdbcType;

    /**  
     * 例如4, 对应int类型
     */  
    private Integer dataType;

    /**  
     * 例如 int
     */  
    private String typeName;

    private Integer columnSize;

    private Integer decimalDigit;

    private Boolean nullable;

    private String remarks;

    private Boolean autoincrement;

    private Generator<?> generator;

    public Class getJavaType() {
        return JdbcTypeEnum.getJavaType(typeName);
    }

    public Boolean getAutoincrement() {
        return autoincrement;
    }

    public void setAutoincrement(Boolean autoincrement) {
        this.autoincrement = autoincrement;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(Integer columnSize) {
        this.columnSize = columnSize;
    }

    public Integer getDecimalDigit() {
        return decimalDigit;
    }

    public void setDecimalDigit(Integer decimalDigit) {
        this.decimalDigit = decimalDigit;
    }

    public Boolean getNullable() {
        return nullable;
    }

    public void setNullable(Boolean nullable) {
        this.nullable = nullable;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Generator getGenerator() {
        return generator;
    }

    public String getTableCat() {
        return tableCat;
    }

    public void setTableCat(String tableCat) {
        this.tableCat = tableCat;
    }

    public void setGenerator(Generator<?> generator) {
        this.generator = generator;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Column.class.getSimpleName() + "[", "]")
                .add("tableCat='" + tableCat + "'")
                .add("tableName='" + tableName + "'")
                .add("columnName='" + columnName + "'")
                .add("jdbcType='" + jdbcType + "'")
                .add("dataType=" + dataType)
                .add("typeName='" + typeName + "'")
                .add("columnSize=" + columnSize)
                .add("decimalDigit=" + decimalDigit)
                .add("nullable=" + nullable)
                .add("remarks='" + remarks + "'")
                .add("autoincrement=" + autoincrement)
                .add("generator=" + generator)
                .toString();
    }
}
