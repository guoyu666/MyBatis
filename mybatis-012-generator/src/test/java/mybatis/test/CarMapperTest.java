package mybatis.test;

import mybatis.mapper.CarMapper;
import mybatis.pojo.Car;
import mybatis.pojo.CarExample;
import mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class CarMapperTest {
    @Test
    public void testDeleteByPrimaryKey(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        int count = carMapper.deleteByPrimaryKey(11L);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }

    // CarExample类负责封装查询条件
    @Test
    public void testSelect(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        // 执行查询
        // 1.查询一个
        Car car = carMapper.selectByPrimaryKey(1L);
        System.out.println(car);
        // 2.查询所有(selectByExample,根据条件查询，如果条件是null表示没有条件)
        List<Car> cars = carMapper.selectByExample(null);
        for (Car car1 : cars) {
            System.out.println(car1);
        }
        // 3.按照条件进行查询
        // 封装条件，通过CarExample对象来封装查询条件
        CarExample carExample = new CarExample();
        //  通过carExample.createCriteria()方法来创建查询条件
        carExample.createCriteria().andBrandEqualTo("甲壳虫");
        sqlSession.close();

    }
}
