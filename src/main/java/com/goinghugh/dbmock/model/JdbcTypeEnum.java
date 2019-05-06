package com.goinghugh.dbmock.model;

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
