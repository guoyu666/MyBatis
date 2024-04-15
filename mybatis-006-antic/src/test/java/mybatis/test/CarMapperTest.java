package mybatis.test;

import mybatis.mapper.CarMapper;
import mybatis.pojo.Car;
import mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class CarMapperTest {
    @Test
    public void testSelectByCarType() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        // carMapper实际上就是daoImpl对象
        // 底层不但为CarMapper接口生成了字节码，并且还new实现类对象了
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = carMapper.selectByCarType("油车");
        for (Car car : cars) {
            System.out.println(car);
        }
        sqlSession.close();
    }

    @Test
    public void testSelectAllByAscOrDesc(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = carMapper.selectAllByAscOrDesc("desc");
        for (Car car : cars) {
            System.out.println(car);
        }
        sqlSession.close();
    }

    @Test
    public void testInsertCarUseGeneratedKeys(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        Car car = new Car(null,"9991","小米su7",29.0,"2024-3-1","电动车");
        carMapper.insertCarUseGeneratedKeys(car);

        System.out.println(car);

        sqlSession.commit();
        sqlSession.close();
    }
}
