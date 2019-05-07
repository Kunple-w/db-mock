package com.goinghugh.dbmock;

import com.goinghugh.dbmock.model.Column;
import com.goinghugh.dbmock.model.TableStructure;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yongxu wang
 * @date 2019-05-07 15:14
 **/
public class SimpleMockWriter implements MockWriter {
    private static final Logger logger = LoggerFactory.getLogger(SimpleMockWriter.class);

    @Override
    public int write(TableStructure structure, int num) {
        String createSql = structure.getCreateSql();
        List<Object[]> batchArgs = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Object[] objects = genRow(structure);
            batchArgs.add(objects);
        }
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringJdbcDemo.Config.class);
        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
        long begin = System.currentTimeMillis();
        int[] ints = jdbcTemplate.batchUpdate(createSql, batchArgs);
        long elapsedTime = System.currentTimeMillis() - begin;
        logger.info("执行量: {}, 耗时: {}", batchArgs.size(), elapsedTime);
        int count = 0;
        for (int i = 0; i < ints.length; i++) {
            if (ints[i] > 0) {
                count++;
            }
        }
        logger.info("欲插入: {}, 成功插入: {}, 耗时: {}ms", batchArgs.size(), count, elapsedTime);
        return count;
    }

    public static void main(String[] args) {
        Connection conn = DataBaseReader.getConn();
        DataBaseReader dataBaseReader = new DataBaseReader();
        SimpleMockWriter simpleMockWriter = new SimpleMockWriter();

        logger.info("开始插入数据");
        simpleMockWriter.write(dataBaseReader.getTableStructure(conn, "blog", "student"), 100000);
    }

    public Object[] genRow(TableStructure structure) {
        List<Column> collect = structure.getColumns().stream().filter(column -> !column.getAutoincrement()).collect(Collectors.toList());
        Object[] objects = new Object[collect.size()];
        for (Column column : collect) {
            int columnIndex = structure.getColumnIndex(column);
            objects[columnIndex - 1] = column.getGenerator().next();
        }
        return objects;
    }
}
