package com.goinghugh.dbmock;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

/**
 * Int 类型生成器
 *
 * @author yongxu wang
 * @date 2019-05-05 21:52
 **/
public class IntGenerator implements Generator<Integer> {
    private Integer min;

    private Integer max;

    public IntGenerator(Integer min, Integer max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public Integer next() {
        return RandomUtils.nextInt(min, max);
    }

    public static void main(String[] args) {
        Generator<Integer> generator = new IntGenerator(10, Integer.MAX_VALUE);
        for (int i = 0; i < 10; i++) {
            System.out.println(generator.next());
        }
    }
}
