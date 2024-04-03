package junit.service;

import org.junit.Assert;
import org.junit.Test;

/**
 * 单元测试类
 */
public class MathServiceTest {  // 名字规范，你要测试的类名+
    /*
      单元测试方法写多少个呢？
      -- 一般是一个业务方法对应一个测试方法
      -- 测试方法的规范：public void testXxxx(){}
      -- 测试方法的方法名：以test开始，假设测试的方法是sum,这个测试方法名：testSum
      -- @Test注解非常重要，这个注解标注的方法就是一个单元测试的方法
     */
    @Test
    public void testSum(){
        /*
        单元测试中有两个重要的概念：
        1.实际值：被测试业务方法的真正执行结果
        2.期望值：执行这个业务方法之后，你期望的执行结果是什么
         */
        MathService mathService = new MathService();
        int actual = mathService.sum(1, 2);
        // 加断言进行测试
        Assert.assertEquals(3,actual);
    }

    @Test
    public void testSub(){
        MathService mathService = new MathService();
        int actual = mathService.sub(5, 2);
        // 加断言进行测试
        Assert.assertEquals(3,actual);
    }
}
