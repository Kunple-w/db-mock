package com.goinghugh.dbmock;

import com.goinghugh.dbmock.model.TableStructure;
import com.goinghugh.dbmock.utils.ColumnUtils;

/**
 * 抽象writer
 *
 * @author yongxu wang
 * @date 2019-05-21 15:55
 **/
public abstract class AbstractWriter implements DbWriter {
    protected TableStructure structure;

    public AbstractWriter(TableStructure structure) {
        this.structure = structure;
    }

    @Override
    public void before() {
        structure.getColumns().forEach(ColumnUtils::autoGenerator);
    }


    @Override
    public int write(int rowNum) {
        before();
        int i = insertInto(rowNum);
        after();
        return i;
    }

    /**
     * 插入n行数据
     *
     * @param rowNum n行
     * @return int 成功插入多少行
     * @author yongxu wang
     * @date 2019/5/21 下午4:10
     */
    public abstract int insertInto(int rowNum);

    @Override
    public void after() {

    }
}
