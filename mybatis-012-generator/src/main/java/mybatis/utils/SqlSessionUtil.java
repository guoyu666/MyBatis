package mybatis.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * MyBatis工具类
 *
 * @author GuoYu
 * @version 1.0
 * @since 1.0
 */
public class SqlSessionUtil {
    /*
    工具类的构造方法一般都是私有化的
    工具类中的所有方法 都是静态的，直接采用类名即可调用，不需要new对象
    为了防止new对象，构造方法私有化
     */
    private SqlSessionUtil() {
    }

    private static final SqlSessionFactory sqlSessionFactory;

    // 类加载时执行
    // SqlSessionUtil工具类在进行第一次加载的时候，解析mybatis-config.xml文件，创建SqlSessionFactory对象
    static {
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 全局的，服务器级别的，一个服务器中定义一个即可
    // 为什么把SqlSession对象放到ThreadLocal当中呢？为了保证一个线程对应一个SqlSession
    private static final ThreadLocal<SqlSession> local = new ThreadLocal<>();

    /*
      获取会话对象
     */
    public static SqlSession openSession() {
        SqlSession sqlSession = local.get();
        if (sqlSession == null) {
            sqlSession = sqlSessionFactory.openSession();
            // 将sqlSession对象绑定到当前线程上
            local.set(sqlSession);
        }
        return sqlSession;
    }

    /*
     * 关闭会话对象
     */
    public static void closeSession(SqlSession sqlSession) {
        sqlSession = local.get();
        if (sqlSession != null) {
            sqlSession.close();
            // 注意移除SqlSession对象和当前线程的绑定关系
            // 因为Tomcat服务器支持线程池，也就是说，用过的线程对象t1，可能下一次还会使用这个t1线程
            local.remove();
        }
    }
}
