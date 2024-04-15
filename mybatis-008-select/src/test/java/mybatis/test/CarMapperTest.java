package mybatis.test;

import mybatis.mapper.CarMapper;
import mybatis.pojo.Car;
import mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class CarMapperTest {
    @Test
    public void testSelectById(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        Car car = carMapper.selectById(1L);
        System.out.println(car);
        sqlSession.close();
    }

    @Test
    public void testSelectAll(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        List<Car> carList = carMapper.selectAll();
        for (Car car : carList) {
            System.out.println(car);
        }
        sqlSession.close();
    }

    @Test
    public void testSelectByBrandLike(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        // TooManyResultsException
        // 什么意思？你期望的结果是返回一条记录，但是实际的SQL语句在执行的时候返回的记录条数是多条
        Car car = carMapper.selectByBrandLike("比亚迪");
        System.out.println(car);
        sqlSession.close();
    }

    @Test
    public void testSelectByIdReturnMap(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        Map<String, Object> carMap = carMapper.selectByIdReturnMap(1L);
        System.out.println(carMap);
        sqlSession.close();
    }

    @Test
    public void testSelectAllReturnListMap(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        List<Map<String, Object>> maps = carMapper.selectAllReturnListMap();
        for (Map<String, Object> map : maps) {
            System.out.println(map);
        }
        sqlSession.close();
    }

    @Test
    public void testSelectMapReturnMap(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        Map<Long, Map<String, Object>> map = carMapper.selectMapReturnMap();
        System.out.println(map);
        sqlSession.close();
    }

    @Test
    public void testSelectAllByResultMap(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = carMapper.selectAllByResultMap();
        for (Car car : cars) {
            System.out.println(car);
        }
        sqlSession.close();
    }

    @Test
    public void testSelectAllByMapUnderscoreToCameCase(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = carMapper.SelectAllByMapUnderscoreToCamelCase();
        for (Car car : cars) {
            System.out.println(car);
        }
        sqlSession.close();
    }

    @Test
    public void testSelectTotal(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        Long l = carMapper.selectTotal();
        System.out.println(l);
        sqlSession.close();
    }
}
