package mybatis.test;

import mybatis.mapper.StudentMapper;
import mybatis.pojo.Student;
import mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class StudentMapperTest {
    @Test
    public void testSelectById(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        Student student = studentMapper.selectById(1);
        System.out.println(student);
        sqlSession.close();
    }

    @Test
    public void testSelectByIdAssociation(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        Student student = studentMapper.selectByIdAssociation(2);
        System.out.println(student);
        sqlSession.close();
    }

    @Test
    public void testSelectByIdStep1(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        Student student = studentMapper.selectByIdStep1(3);
        System.out.println(student);
        sqlSession.close();
    }
}
