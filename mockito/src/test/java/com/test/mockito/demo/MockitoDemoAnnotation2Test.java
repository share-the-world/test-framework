package com.test.mockito.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MockitoDemoAnnotation2Test {

    @Mock
    private List mockList;
    @Mock
    private Comparable comparable;

    @Test
    public void shorthand(){
        mockList.add(1);
        verify(mockList).add(1);
    }
    @Test
    public void with_arguments(){
        //预设根据不同的参数返回不同的结果
        when(comparable.compareTo("Test")).thenReturn(1);
        when(comparable.compareTo("Omg")).thenReturn(2);
        assertEquals(1, comparable.compareTo("Test"));
        assertEquals(2, comparable.compareTo("Omg"));
        //对于没有预设的情况会返回默认值
        assertEquals(0, comparable.compareTo("Not stub"));
    }

    @Test
    public void with_unspecified_arguments(){
        //匹配任意参数
        when(mockList.get(anyInt())).thenReturn(1);
        when(mockList.contains(argThat(new IsValid()))).thenReturn(true);
        assertEquals(1, mockList.get(1));
        assertEquals(1, mockList.get(999));
        assertTrue(mockList.contains(1));
        assertTrue(!mockList.contains(3));
    }

    private class IsValid extends ArgumentMatcher<List> {
        @Override
        public boolean matches(Object o) {
            return Objects.equals(o, 1) || Objects.equals(o, 2);
        }
    }

    @Test
    public void argumentMatchersTest(){
        //创建mock对象
        List<String> mock = mock(List.class);

        //argThat(Matches<T> matcher)方法用来应用自定义的规则，可以传入任何实现Matcher接口的实现类。
        when(mock.addAll(argThat(new IsListofTwoElements()))).thenReturn(true);

        mock.addAll(Arrays.asList("one","two","three"));
        //IsListofTwoElements用来匹配size为2的List，因为例子传入List为三个元素，所以此时将失败。
        verify(mock).addAll(argThat(new IsListofTwoElements()));
    }

    class IsListofTwoElements extends ArgumentMatcher<List> {
        public boolean matches(Object list) {
            return((List)list).size()==2;
        }
    }
}