package com.test.mockito.demo;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.List;

public class MockitoDemoTest {

    @Test
    public void verify_behaviour() {
        //模拟创建一个List对象
        List mock = Mockito.mock(List.class);
        //使用mock的对象
        mock.add(1);
        mock.clear();
        //验证add(1)和clear()行为是否发生
        Mockito.verify(mock).add(1);
        Mockito.verify(mock).clear();
    }

    @Test
    public void when_thenReturn() {
        //mock一个Iterator类
        Iterator iterator = Mockito.mock(Iterator.class);
        //预设当iterator调用next()时第一次返回hello，第n次都返回world
        Mockito.when(iterator.next()).thenReturn("hello").thenReturn("world");
        //使用mock的对象
        String result = iterator.next() + " " + iterator.next() + " " + iterator.next();
        //验证结果
        Assert.assertEquals("hello world world", result);
    }

    @Test(expected = IOException.class)
    public void when_thenThrow() throws IOException {
        OutputStream outputStream = Mockito.mock(OutputStream.class);
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        //预设当流关闭时抛出异常
        Mockito.doThrow(new IOException()).when(outputStream).close();
        outputStream.close();
    }

    /**
     * 如果通过RETURNS_SMART_NULLS参数创建的mock对象在没有调用stubbed方法时会返回SmartNull。
     * 例如：返回类型是String，会返回"";
     * 是int，会返回0；
     * 是List，会返回空的List。
     * 另外，在控制台窗口中可以看到SmartNull的友好提示
     */
    @Test
    public void returnsSmartNullsTest() {
        List mock = Mockito.mock(List.class, Mockito.RETURNS_SMART_NULLS);
        System.out.println(mock.get(0));

        //使用RETURNS_SMART_NULLS参数创建的mock对象，不会抛出NullPointerException异常。另外控制台窗口会提示信息“SmartNull returned by unstubbed get() method on mock”
        System.out.println(mock.toArray().length);
    }

    @Test
    public void deepstubsTest() {
        Account account = Mockito.mock(Account.class, Mockito.RETURNS_DEEP_STUBS);
        Mockito.when(account.getRailwayTicket().getDestination()).thenReturn("Beijing");
        account.getRailwayTicket().getDestination();
        Mockito.verify(account.getRailwayTicket()).getDestination();
        Assert.assertEquals("Beijing", account.getRailwayTicket().getDestination());
    }

    @Test
    public void deepstubsTest2() {
        Account account = Mockito.mock(Account.class);
        RailwayTicket railwayTicket = Mockito.mock(RailwayTicket.class);
        Mockito.when(account.getRailwayTicket()).thenReturn(railwayTicket);
        Mockito.when(railwayTicket.getDestination()).thenReturn("Beijing");

        account.getRailwayTicket().getDestination();
        Mockito.verify(account.getRailwayTicket()).getDestination();
        Assert.assertEquals("Beijing", account.getRailwayTicket().getDestination());
    }
    @Test(expected = RuntimeException.class)
    public void doThrow_when(){
        List list = Mockito.mock(List.class);
        Mockito.doThrow(new RuntimeException()).when(list).add(1);
        list.add(1);
    }

}