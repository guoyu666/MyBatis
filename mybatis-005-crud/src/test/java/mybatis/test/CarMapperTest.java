package mybatis.test;

import mybatis.mapper.CarMapper;
import mybatis.pojo.Car;
import mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class CarMapperTest {
    @Test
    public void testInsert() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        // 面向接口，获取接口的代理对象
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        Car car = new Car(null,"8654","BMW",4.0,"2022-10-7","油电混合");
        int count = carMapper.insert(car);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testDeleteById(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        int count = carMapper.deleteById(6L);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testUpdate(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        Car car = new Car(1L,"1314","保时捷",45.0,"2023-1-1","油车");
        int count = carMapper.update(car);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }
}
