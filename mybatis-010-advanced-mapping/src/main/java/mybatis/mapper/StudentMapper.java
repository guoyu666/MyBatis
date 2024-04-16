package mybatis.mapper;

import mybatis.pojo.Student;

public interface StudentMapper {
    /**
     * 根据id获取学生信息，同时获取学生关联的班级信息
     * @param id 学生的id
     * @return 学生对象，但是学生对象中含有班级对象
     */
    Student selectById(Integer id);


    /**
     * 一条SQL语句，association
     * @param id
     * @return
     */
    Student selectByIdAssociation(Integer id);

    /**
     * 分步查询第一步：先根据学生的sid查询学生的信息
     * @param id
     * @return
     */
    Student selectByIdStep1(Integer id);
}
