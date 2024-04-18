package mybatis.mapper;

import mybatis.pojo.Car;

public interface CarMapper {
    /**
     * 根据id获取Car信息
     * @param id
     * @return
     */
    Car selectById(Long id);

    Car selectById2(Long id);
}
