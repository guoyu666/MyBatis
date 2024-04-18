package mybatis.test;

import mybatis.mapper.ClazzMapper;
import mybatis.pojo.Clazz;
import mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class ClazzMapperTest {
    @Test
    public void testSelectByCollection(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        ClazzMapper mapper = sqlSession.getMapper(ClazzMapper.class);
        Clazz clazz = mapper.selectByCollection(1001);
        System.out.println(clazz);
        sqlSession.close();
    }

    @Test
    public void testSelectByStep1(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        ClazzMapper mapper = sqlSession.getMapper(ClazzMapper.class);
        Clazz clazz = mapper.selectByStep1(1001);
        System.out.println(clazz);
        sqlSession.close();
    }
}
