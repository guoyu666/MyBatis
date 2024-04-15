package mybatis.test;

import com.google.protobuf.RpcUtil;
import mybatis.mapper.StudentMapper;
import mybatis.pojo.Student;
import mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class StudentMapperTest {
    @Test
    public void testSelectById(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = studentMapper.selectById(1L);
        for (Student student : students) {
            System.out.println(student);
        }
        sqlSession.close();
    }

    @Test
    public void testSelectByName(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = studentMapper.selectByName("郭雨");
        for (Student student : students) {
            System.out.println(student);
        }
        sqlSession.close();
    }

    @Test
    public void testSelectByBirth() throws ParseException {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        // 把字符串日期转换成Date类型
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date birth = format.parse("1997-11-04");

        List<Student> students = studentMapper.selectByBirth(birth);
        for (Student student : students) {
            System.out.println(student);
        }
        sqlSession.close();
    }

    @Test
    public void testSelectBySex(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        Character sex = Character.valueOf('男');
        List<Student> students = studentMapper.selectBySex(sex);
        for (Student student : students) {
            System.out.println(student);
        }
        sqlSession.close();
    }

    @Test
    public void testInsertStudentByMap(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","LuoYiYan");
        map.put("age", 20);
        map.put("height",1.81);
        map.put("sex",'女');
        map.put("birth",new Date());
        studentMapper.insertStudentByMap(map);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testInsertStudentByPOJO(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        Student student = new Student(null,"张飞",32,1.86,new Date(),'男');
        studentMapper.insertStudentByPOJO(student);
        sqlSession.commit();
    }

    @Test
    public void testSelectByNameAndSex(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = studentMapper.selectByNameAndSex("张飞", '男');
        for (Student student : students) {
            System.out.println(student);
        }
        sqlSession.close();
    }

    @Test
    public void testSelectByNameAndSex2(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        // studentMapper实际上指向了代理对象
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        // studentMapper是代理对象
        // selectByNameAndSex2是代理方法
        List<Student> students = studentMapper.selectByNameAndSex2("张三", '男');
        for (Student student : students) {
            System.out.println(student);
        }
        sqlSession.close();
    }
}
