package mybatis.mapper;

import mybatis.pojo.Clazz;

public interface ClazzMapper {
    /**
     * 分步查询第二步：根据cid获取班级信息
     * @param id
     * @return
     */
    Clazz selectByIdStep2(Integer id);
}
