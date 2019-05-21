package com.goinghugh.dbmock;

import com.goinghugh.dbmock.model.TableStructure;

import java.util.List;

/**
 * database reader
 *
 * @author yongxu wang
 * @date 2019-05-21 11:53
 **/
public interface DbReader {

    /**
     * 读取表的结构
     *
     * @return 表结构，根据数据库和表名字，可能不止一个数据表,返回所有的表
     * @author yongxu wang
     * @date 2019/5/21 上午11:54
     */
    List<TableStructure> read();
}
