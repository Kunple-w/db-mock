package com.goinghugh.dbmock;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * String 类型生成器
 *
 * @author yongxu wang
 * @date 2019-05-05 21:39
 **/
public class StringGenerator implements Generator<String> {
    private Integer min;
    private Integer max;

    public StringGenerator(Integer min, Integer max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public String next() {
        return RandomStringUtils.randomAlphabetic(min, max);
    }

    public static void main(String[] args) {
        Generator<String> generator = new StringGenerator(10, 20);
        for (int i = 0; i < 10; i++) {
            System.out.println(generator.next());
        }
    }
}
