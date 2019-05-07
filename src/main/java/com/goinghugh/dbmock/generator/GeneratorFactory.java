package com.goinghugh.dbmock.generator;

import com.goinghugh.dbmock.generator.Generator;
import com.goinghugh.dbmock.generator.IntGenerator;
import com.goinghugh.dbmock.generator.StringGenerator;
import com.goinghugh.dbmock.model.JdbcTypeEnum;

/**
 * 选择器工厂
 *
 * @author yongxu wang
 * @date 2019-05-07 09:53
 **/
public class GeneratorFactory {

    public static Generator get(String jdbc) {
        Class clazz = JdbcTypeEnum.getJavaType(jdbc);
        if (clazz.equals(String.class)) {
            return new StringGenerator();
        } else if (clazz.equals(Integer.class)) {
            return new IntGenerator();
        }
        return new StringGenerator();
    }
}
