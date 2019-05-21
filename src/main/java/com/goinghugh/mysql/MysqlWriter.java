package com.goinghugh.mysql;

import com.goinghugh.dbmock.AbstractWriter;
import com.goinghugh.dbmock.config.BeanConfig;
import com.goinghugh.dbmock.model.Column;
import com.goinghugh.dbmock.model.TableStructure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yongxu wang
 * @date 2019-05-21 14:56
 **/
public class MysqlWriter extends AbstractWriter {

    private static final Logger logger = LoggerFactory.getLogger(MysqlWriter.class);


    public MysqlWriter(TableStructure table) {
        super(table);
    }

    @Override
    public int insertInto(int rowNum) {
        String insertSql = structure.getInsertSql();
        logger.info("sql : {}", insertSql);
        List<Object[]> batchArgs = new ArrayList<>();
        for (int i = 0; i < rowNum; i++) {
            Object[] objects = genRow(structure);
            batchArgs.add(objects);
        }
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
        long begin = System.currentTimeMillis();
        int[] ints = jdbcTemplate.batchUpdate(insertSql, batchArgs);
        long elapsedTime = System.currentTimeMillis() - begin;
        int count = 0;
        for (int anInt : ints) {
            if (anInt > 0) {
                count++;
            }
        }
        logger.info("欲插入: {} row, 成功插入: {} row, 耗时: {}ms, 速率: {} row/s", batchArgs.size(), count, elapsedTime, ((float) count) / elapsedTime * 1000);
        return count;
    }

    private Object[] genRow(TableStructure structure) {
        List<Column> collect = structure.getColumns().stream().filter(column -> !column.getAutoincrement()).collect(Collectors.toList());
        Object[] objects = new Object[collect.size()];
        for (Column column : collect) {
            int columnIndex = structure.getColumnIndex(column);
            objects[columnIndex - 1] = column.getGenerator().next();
        }
        return objects;
    }
}
