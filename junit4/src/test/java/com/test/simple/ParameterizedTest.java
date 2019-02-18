package com.test.simple;

import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;




/**
 * @author heshiyuan
 * @description <p></p>
 * @path test-framework/com.test.simple
 * @date 2018-12-11 13:50
 * Copyright (c) 2018 heshiyuan@huobi.com All rights reserved.
 */
@RunWith(value = Parameterized.class)
public class ParameterizedTest {

    private int expected;
    private int oneParam;
    private int twoParam;
    Calculator calculator = null;
    @Before
    public void before(){
        calculator = new Calculator();
    }

    @Parameterized.Parameters
    public static Collection<Integer[]> getTestParameters(){
        return Arrays.asList(new Integer[][]{
                {2, 1, 1},
                {4, 2, 2},
                {6, 1, 5}
        });
    }

    public ParameterizedTest(int expected, int oneParam, int twoParam){
        this.expected = expected;
        this.oneParam = oneParam;
        this.twoParam = twoParam;
    }

    @Test
    public void add() {
        Assert.assertEquals(expected, calculator.add(oneParam, twoParam), 0);
    }
}
