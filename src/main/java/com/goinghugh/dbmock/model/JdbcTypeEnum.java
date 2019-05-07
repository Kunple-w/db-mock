package com.goinghugh.dbmock.model;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

/**
 * java type 和jdbc type
 *
 * @author yongxu wang
 * @date 2019-05-06 10:46
 **/
public enum JdbcTypeEnum {

    CHAR("CHAR", String.class),
    VARCHAR("VARCHAR", String.class),
    INT("INT", Integer.class),
    LONGVARCHAR("LONGVARCHAR", String.class),
    NUMERIC("NUMERIC", java.math.BigDecimal.class),
    DECIMAL("DECIMAL", java.math.BigDecimal.class),
    BIT("BIT", boolean.class),
    BOOLEAN("BOOLEAN", boolean.class),
    TINYINT("TINYINT", byte.class),
    SMALLINT("SMALLINT", short.class),
    INTEGER("INTEGER", int.class),
    BIGINT("BIGINT", long.class),
    REAL("REAL", float.class),
    FLOAT("FLOAT", double.class),
    DOUBLE("DOUBLE", double.class),
    BINARY("BINARY", byte[].class),
    VARBINARY("VARBINARY", byte[].class),
    LONGVARBINARY("LONGVARBINARY", byte[].class),
    DATE("DATE", Date.class),
    TIME("TIME", Time.class),
    TIMESTAMP("TIMESTAMP", Timestamp.class),
//    CLOB("CLOB", Clob),
//    BLOB("BLOB", Blob),
//    ARRAY("ARRAY", Array),
//    DISTINCT("DISTINCT", ),
//    STRUCT("STRUCT", ),
//    REF("REF", ),
//    DATALINK("DATALINK", ),
    ;

    private String jdbcType;

    private Class javaType;

    JdbcTypeEnum(String jdbcType, Class javaType) {
        this.jdbcType = jdbcType;
        this.javaType = javaType;
    }

    public static Class getJavaType(String jdbcType) {
        for (JdbcTypeEnum e : values()) {
            if (e.jdbcType.equals(jdbcType)) {
                return e.javaType;
            }
        }
        throw new IllegalArgumentException("jdbcType: " + jdbcType + " 未定义");
    }

    public static String getJdbcType(Class clazz) {
        for (JdbcTypeEnum e : values()) {
            if (e.javaType.equals(clazz)) {
                return e.jdbcType;
            }
        }
        throw new IllegalArgumentException("javaType: " + clazz + " 未定义");
    }
}
