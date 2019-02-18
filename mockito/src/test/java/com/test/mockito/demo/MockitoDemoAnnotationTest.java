package com.test.mockito.demo;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

public class MockitoDemoAnnotationTest {

    @Mock
    private List mockList;

    public MockitoDemoAnnotationTest(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shorthand(){
        mockList.add(1);
        verify(mockList).add(1);
    }

}