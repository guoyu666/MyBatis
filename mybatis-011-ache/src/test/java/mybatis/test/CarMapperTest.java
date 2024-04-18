package mybatis.test;

import mybatis.mapper.CarMapper;
import mybatis.pojo.Car;
import mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;

public class CarMapperTest {
    /**
     * 思考：什么情况下不走缓存？
     *      第一次DQL查询语句和第二次DQL查询语句之间你做了以下两件事中的任意一件，都会让一级缓存清空：
     *      1.执行了sqlSession的clearCache()方法，这是手动清空缓存
     *      2.执行了INSERT或者DELETE或者UPDATE语句，不管你是哪张表的数据，都会清空一级缓存
     */
    @Test
    public void testSelectById(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        Car car1 = carMapper.selectById(1L);
        System.out.println(car1);

        Car car2 = carMapper.selectById(1L);
        System.out.println(car2);
        sqlSession.close();
    }

    @Test
    public void testSelectById2() throws IOException {
        // 这里只有一个SqlSessionFactory对象，二级缓存对应的就是SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        CarMapper carMapper1 = sqlSession1.getMapper(CarMapper.class);
        CarMapper carMapper2 = sqlSession2.getMapper(CarMapper.class);

        // 这行代码执行结束的时候，实际上数据是缓存到一级缓存当中了(SqlSession1是一级缓存）
        Car car1 = carMapper1.selectById(1L);
        System.out.println(car1);

        // 如果这里不关闭SqlSession1对象的话，二级缓存中还是没有数据的

        // 如果执行了这行代码,sqlSession1的一级缓存中的数据会放到二级缓存中
        sqlSession1.close();

        // 这行代码执行结束之后，实际上数据会缓存到一级缓存当中(SqlSession2是一级缓存)
        Car car2 = carMapper2.selectById(1L);
        System.out.println(car2);

        // 程序执行到这里的时候，会将sqlSession1这个一级缓存中的数据写入到二级缓存中
        // sqlSession1.close();
        // 程序执行到这里的时候，会将sqlSession2这个一级缓存中的数据写入到二级缓存中
        sqlSession2.close();
    }
}
