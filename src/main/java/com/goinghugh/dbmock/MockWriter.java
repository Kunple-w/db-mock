package com.goinghugh.dbmock;

import com.goinghugh.dbmock.model.TableStructure;

/**
 * @author yongxu wang
 * @date 2019-05-07 11:58
 **/
public interface MockWriter {
    /**
     * 根据表结构写入随机数据
     * 列的数根据column中的generator生成
     *
     * @param structure 表结构
     * @param num       生成的数量
     * @return int
     * @author yongxu wang
     * @date 2019/5/7 上午11:59
     */
    int write(TableStructure structure, int num);
}
