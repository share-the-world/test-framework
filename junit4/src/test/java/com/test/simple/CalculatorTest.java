package com.test.simple;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path test-framework/com.test.temp
 * @date 2018-12-11 13:42
 * Copyright (c) 2018 heshiyuan@huobi.com All rights reserved.
 */
public class CalculatorTest {
    Calculator calculator = null;
    @Before
    public void before(){
        calculator = new Calculator();
    }
    @Test
    public void add() {
        int a = 10, b = 20;
        int sum = calculator.add(a, b);
        Assert.assertEquals("两个数不相等", 340, sum);
    }
}