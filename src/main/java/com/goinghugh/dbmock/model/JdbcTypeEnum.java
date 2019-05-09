package com.goinghugh.dbmock.model;

import com.goinghugh.dbmock.generator.*;

import java.math.BigDecimal;
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

    CHAR("CHAR", String.class, StringGenerator.class),
    VARCHAR("VARCHAR", String.class, StringGenerator.class),
    INT("INT", Integer.class, IntGenerator.class),
    INT_UNSIGNED ("INT UNSIGNED", Integer.class, UnsignedIntGenerator.class),
    LONGVARCHAR("LONGVARCHAR", String.class, StringGenerator.class),
    LONGTEXT("LONGTEXT", String.class, StringGenerator.class),
    NUMERIC("NUMERIC", java.math.BigDecimal.class, BigDecimalGenerator.class),
    DECIMAL("DECIMAL", java.math.BigDecimal.class, BigDecimalGenerator.class),
    BIT("BIT", boolean.class, BoolGenerator.class),
    BOOLEAN("BOOLEAN", boolean.class, BoolGenerator.class),
//    TINYINT("TINYINT", byte.class),
    SMALLINT("SMALLINT", short.class, ShortGenerator.class),
    INTEGER("INTEGER", int.class, IntGenerator.class),
    BIGINT("BIGINT", long.class, LongGenerator.class),
    REAL("REAL", float.class, FloatGenerator.class),
    FLOAT("FLOAT", double.class, DoubleGenerator.class),
    DOUBLE("DOUBLE", double.class, DoubleGenerator.class),
    //    BINARY("BINARY", byte[].class, ),
//    VARBINARY("VARBINARY", byte[].class),
//    LONGVARBINARY("LONGVARBINARY", byte[].class),
    DATETIME("DATETIME", Date.class, DateGenerator.class),
    TIMESTAMP("TIMESTAMP", Timestamp.class, DateGenerator.class),
    ;

    private String jdbcType;

    private Class javaType;

    private Class<? extends Generator> generator;

    JdbcTypeEnum(String jdbcType, Class javaType, Class<? extends Generator> generator) {
        this.jdbcType = jdbcType;
        this.javaType = javaType;
        this.generator = generator;
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
