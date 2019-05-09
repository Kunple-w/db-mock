package com.goinghugh.dbmock.generator;

import org.apache.commons.lang3.RandomUtils;

import java.util.Date;

/**
 * 日期生成器
 *
 * @author yongxu wang
 * @date 2019-05-09 10:48
 **/
public class DateGenerator implements Generator<Date> {


    @Override
    public Date next() {
        long endExclusive = System.currentTimeMillis() + 10000000;
        return new Date(RandomUtils.nextLong(0, endExclusive));
    }

    @Override
    public void setMax(int max) {
    }

    @Override
    public void setMin(int min) {

    }
}
