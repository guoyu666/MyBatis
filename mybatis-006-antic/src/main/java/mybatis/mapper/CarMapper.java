package mybatis.mapper;

import mybatis.pojo.Car;

import java.util.List;

public interface CarMapper {
    /**
     * 根据汽车类型获取汽车信息
     * @param carType
     * @return
     */
    List<Car> selectByCarType(String carType);

    /**
     * 查询所有的汽车信息，然后通过asc升序，desc降序
     * @param ascOrDesc
     * @return
     */
    List<Car> selectAllByAscOrDesc(String ascOrDesc);

    /**
     * 插入Car信息，并且使用生成的主键值
     * @param car
     * @return
     */
    int insertCarUseGeneratedKeys(Car car);
}
