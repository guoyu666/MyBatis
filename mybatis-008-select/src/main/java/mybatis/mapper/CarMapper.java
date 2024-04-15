package mybatis.mapper;

import mybatis.pojo.Car;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface CarMapper {
    /**
     * 根据id查询Car信息
     * @param id
     * @return
     */
    Car selectById(Long id);

    /**
     * 获取所有的Car
     * @return
     */
    List<Car> selectAll();

    /**
     * 根据品牌进行模糊查询
     * 模糊查询的结果可能有多个，但是我采用一个POJO对象接受会有问题吗？
     * @param brand
     * @return
     */
    Car selectByBrandLike(String brand);

    /**
     * 根据ID获取汽车信息，将汽车信息放入Map集合中
     * @param id
     * @return
     */
    Map<String, Object> selectByIdReturnMap(Long id);

    /**
     * 查询所有的Car信息，返回一个存放Map集合的List集合
     * @return
     */
    List<Map<String,Object>> selectAllReturnListMap();

    /**
     * 查询所有的Car,返回一个大Map集合，Map集合的key是每条记录的主键值，Map集合的value是每条记录
     * @param id
     * @return
     */
    @MapKey("id")   // 将查询结果的id值作为整个大Map集合的key
    Map<Long,Map<String,Object>> selectMapReturnMap();

    /**
     * 查询所有的Car信息，使用resultMap进行结果映射
     * @return
     */
    List<Car> selectAllByResultMap();

    /**
     * 查询所有的信息，但是启用了驼峰命名自动映射机制
     * @return
     */
    List<Car> SelectAllByMapUnderscoreToCamelCase();

    /**
     * 获取Car的总记录条数
     * @return
     */
    Long selectTotal();
}
