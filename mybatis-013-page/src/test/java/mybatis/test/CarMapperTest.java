package mybatis.test;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import mybatis.mapper.CarMapper;
import mybatis.pojo.Car;
import mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class CarMapperTest {
    @Test
    public void testPageSize(){
        // 获取每页显示的记录条数
        int pageSize = 3;
        // 显示第几页：页码
        int pageNum = 2;
        // 计算开始下标
        int startIndex = (pageNum - 1) * pageSize;
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = carMapper.selectByPage(startIndex, pageSize);
        for (Car car : cars) {
            System.out.println(car);
        }
        sqlSession.close();
    }

    @Test
    public void testSelectAll(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        // 一定一定要注意，在执行DQL语句之前，开启分页功能
        int pageNum = 2;
        int pageSize = 2;
        PageHelper.startPage(pageNum,pageSize);

        List<Car> cars = carMapper.selectAll();
        // 封装分页信息对象new PageInfo()
        // PageInfo对象是PageHelper插件提供的，用来封装分页 相关的信息的对象
        PageInfo<Car> carPageInfo = new PageInfo<>(cars, 2);
        /*for (Car car : cars) {
            System.out.println(car);
        }*/
        System.out.println(carPageInfo);
        sqlSession.close();
    }
}
