package mybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 采用正规的方式，写一个完整版的MyBatis程序
 */
public class MyBatisCompleteCodeTest {
    public static void main(String[] args) {
        SqlSession sqlSession = null;
        try {
            // 创建SqlSessionFactoryBuilder对象
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

            // 创建SqlSessionFactory对象
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));

            // 创建SqlSession对象
            sqlSession = sqlSessionFactory.openSession();

            // 执行SQL
            int count = sqlSession.insert("insertCar");
            System.out.println("更新了几条记录：" + count);

            // 提交
            sqlSession.commit();
        } catch (Exception e) {
            // 回滚
            if (sqlSession != null){
                sqlSession.rollback();
            }
            e.printStackTrace();
        } finally {
            // 关闭
            if (sqlSession != null){
                sqlSession.close();
            }
        }
    }
}
