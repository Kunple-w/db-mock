package com.goinghugh.dbmock;

/**
 * writer
 *
 * @author yongxu wang
 * @date 2019-05-21 14:54
 **/
public interface DbWriter {

    /**
     * 写入前的准备动作
     *
     * @author yongxu wang
     * @date 2019/5/21 下午2:57
     */
    void before();

    /**
     * 想写入的行数
     *
     * @param rowNum 行数
     * @return boolean
     * @author yongxu wang
     * @date 2019/5/21 下午2:56
     */
    int write(int rowNum);

    /**
     * 完成后的动作
     */
    void after();
}
