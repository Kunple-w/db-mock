package com.goinghugh.dbmock.generator;

import org.apache.commons.lang3.RandomUtils;

/**
 * Int 类型生成器
 *
 * @author yongxu wang
 * @date 2019-05-05 21:52
 **/
public class UnsignedIntGenerator implements Generator<Integer> {

    private Integer min;

    private Integer max;

    @Override
    public Integer next() {
        return RandomUtils.nextInt(0, max);
    }

    @Override
    public void setMax(int max) {
        this.max = max;

    }

    @Override
    public void setMin(int min) {
        this.min = min;
    }
}
