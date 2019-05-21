package com.goinghugh.dbmock.utils;

import com.goinghugh.dbmock.generator.Generator;
import com.goinghugh.dbmock.generator.GeneratorFactory;
import com.goinghugh.dbmock.model.Column;

/**
 * 列工具类
 *
 * @author yongxu wang
 * @date 2019-05-07 15:09
 **/
public class ColumnUtils {

    /**
     * 填充列的生成器
     *
     * @param column 列
     * @author yongxu wang
     * @date 2019/5/7 下午3:10
     */
    public static void autoGenerator(Column column) {
        String jdbcType = column.getTypeName();
        Generator generator = GeneratorFactory.get(jdbcType);
        column.setGenerator(generator);
        Integer columnSize = column.getColumnSize();
        column.getGenerator().setMax(columnSize);
        column.getGenerator().setMin(0);
    }
}
