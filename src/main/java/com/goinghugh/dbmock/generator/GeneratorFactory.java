package com.goinghugh.dbmock.generator;

import com.goinghugh.dbmock.model.JdbcTypeEnum;

import java.util.Date;

/**
 * 选择器工厂
 *
 * @author yongxu wang
 * @date 2019-05-07 09:53
 **/
public class GeneratorFactory {

    private GeneratorFactory() {
    }

    public static Generator get(String jdbc) {
        Class clazz = JdbcTypeEnum.getJavaType(jdbc);
        if (clazz.equals(String.class)) {
            return new StringGenerator();
        } else if (clazz.equals(Integer.class)) {
            return new IntGenerator();
        } else if (clazz.equals(Date.class)) {
            return new DateGenerator();
        }
        return new StringGenerator();
    }
}
