package com.goinghugh.dbmock;

/**
 * 生成器接口
 *
 * @author yongxu wang
 * @date 2019-05-05 21:37
 **/
public interface Generator<G> {

    /**
     * 生成下一个值
     *
     * @return G
     * @author yongxu wang
     * @date 2019/5/5 下午9:38
     */
    G next();
}
