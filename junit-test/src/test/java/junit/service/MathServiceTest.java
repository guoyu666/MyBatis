package junit.service;
import org.junit.Assert;
import org.junit.Test;

/**
 * 单元测试类
 * @author GuoYu
 * @version 1.0
 * @since 1.0
 */
public class MathServiceTest {
    @Test
    public void testSum(){
        // 单元测试中有两个重要的概念：
        // 1.一个是：实际值（被测试业务方法的真正执行结果）
        // 2.一个是：期望值（执行了这个业务方法之后，你期望的执行结果是多少）
        MathService mathService = new MathService();
        // 获取实际值
        int actual = mathService.sum(1, 2);
        // 期望值
        int expected = 3;
        // 加断言进行测试
        Assert.assertEquals(expected,actual);
    }
}
