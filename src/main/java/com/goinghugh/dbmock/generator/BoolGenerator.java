package com.goinghugh.dbmock.generator;

/**
 * @author yongxu wang
 * @date 2019-05-09 11:31
 **/
public class BoolGenerator implements Generator<Boolean>{
    @Override
    public Boolean next() {
        return false;
    }

    @Override
    public void setMax(int max) {

    }

    @Override
    public void setMin(int min) {

    }
}
