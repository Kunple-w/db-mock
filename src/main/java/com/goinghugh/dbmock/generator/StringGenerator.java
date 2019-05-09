package com.goinghugh.dbmock.generator;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * String 类型生成器
 *
 * @author yongxu wang
 * @date 2019-05-05 21:39
 **/
public class StringGenerator implements Generator<String> {
    private final static Integer MAX_LENGTH = 20000;
    private Integer min;
    private Integer max;

    @Override
    public String next() {
        if (max > MAX_LENGTH) {
            max = MAX_LENGTH;
        }
        return RandomStringUtils.randomAlphabetic(min, max);
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
