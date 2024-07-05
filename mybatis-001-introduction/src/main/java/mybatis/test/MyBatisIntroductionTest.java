package mybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * MyBatis入门程序
 * @author GuoYu
 * @version 1.0
 * @since 1.0
 */
public class MyBatisIntroductionTest {
    public static void main(String[] args) throws IOException {
        // 1. 创建SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

        // 2. 创建SqlSessionFactory对象
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");   // Resources.getResourceAsStream默认就是从类的根路径下开始查找资源的
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);   // 一般情况下都是一个数据库对应一个SqlSessionFactory对象

        // 3. 创建SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();  // 如果使用的事务管理器是JDBC的话，底层实际上会执行：conn.setAutoCommit(false);

        // 4. 执行sql
        int count = sqlSession.insert("insertCar"); // 这个"insertCar"必须是sql的id
        // 返回值是影响数据库表当中的记录条数
        System.out.println("插入几条数据：" + count);

        // 5. 提交（mybatis默认采用的事务管理器是JDBC，默认是不提交的，需要手动提交！！）
        sqlSession.commit();

        // 6. 关闭资源（只关闭是不会提交的）
        sqlSession.close();
    }
}
