package mybatis.test;

import mybatis.mapper.CarMapper;
import mybatis.pojo.Car;
import mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CarMapperTest {
    @Test
    public void testSelectByMultiCondition(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        // 假设三个条件都不为空
        List<Car> cars = carMapper.selectByMultiCondition("比亚迪", 29.0, "电动车");
        for (Car car : cars) {
            System.out.println(car);
        }
        sqlSession.close();
    }

    @Test
    public void testSelectByMultiConditionWithWhere(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        // 三个条件都不为空
        List<Car> cars = carMapper.selectByMultiConditionWithWhere("比亚迪", 29.0, "电动车");
        for (Car car : cars) {
            System.out.println(car);
        }
        sqlSession.close();
    }

    @Test
    public void testSelectByMultiConditionWithTrim(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = carMapper.selectByMultiConditionWithTrim("比亚迪", null, "");
        for (Car car : cars) {
            System.out.println(car);
        }
        sqlSession.close();
    }

    @Test
    public void testUpdateById(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        Car car = new Car(1L,null,"丰田霸道",null,null,"油电混合");
        carMapper.updateById(car);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testUpdateBySet(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        Car car = new Car(1L,null,"甲壳虫",null,null,"燃油车");
        carMapper.updateBySet(car);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testSelectByChoose(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = carMapper.selectByChoose("甲壳虫",null,null);
        for (Car car : cars) {
            System.out.println(car);
        }
        sqlSession.close();
    }

    @Test
    public void testDeleteByIds(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        Long[] ids = {7L,8L,9L};
        int count = carMapper.deleteByIds(ids);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testInsertBatch(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        Car car1 = new Car(null,"8989","大众",32.0,"2022-10-3","燃油车");
        Car car2 = new Car(null,"4545","劳斯莱斯",100.0,"1998-5-4","油电混合");
        List<Car> cars = new ArrayList<>();
        cars.add(car1);
        cars.add(car2);
        int count = carMapper.insertBatch(cars);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }
}
