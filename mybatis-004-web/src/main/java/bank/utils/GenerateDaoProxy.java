package bank.utils;
import org.apache.ibatis.javassist.CannotCompileException;
import org.apache.ibatis.javassist.ClassPool;
import org.apache.ibatis.javassist.CtClass;
import org.apache.ibatis.javassist.CtMethod;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 工具类：可以动态生成Dao的实现类（或者可以说动态生成Dao的代理类）
 * @author GuoYu
 * @version 1.0
 * @since 1.0
 */
public class GenerateDaoProxy { // GenerateDaoProxy是mybatis框架的开发者写的
    /**
     * 生成dao接口实现类，并且将实现类的对象创建出来并返回
     * @param daoInterface
     * @return
     */
    public static Object generate(SqlSession sqlSession, Class daoInterface){
        // 类池
        ClassPool pool = ClassPool.getDefault();
        // 制造类
        CtClass ctClass = pool.makeClass(daoInterface.getName() + "Proxy");
        // 制造接口
        CtClass ctInterface = pool.makeInterface(daoInterface.getName());
        // 实现接口
        ctClass.addInterface(ctInterface);
        // 实现接口中的方法
        Method[] methods = daoInterface.getDeclaredMethods();
        Arrays.stream(methods).forEach(method -> {
            // method是接口中的抽象方法
            // 将method这个抽象方法进行实现
            try {
                StringBuilder methodCode = new StringBuilder();
                methodCode.append("public ");
                methodCode.append(method.getGenericReturnType().getTypeName());
                methodCode.append(" ");
                methodCode.append(method.getName());
                methodCode.append("(");
                // 需要方法的形式参数列表
                Class<?>[] parameterTypes = method.getParameterTypes();
                for (int i = 0; i < parameterTypes.length; i++) {
                    methodCode.append(parameterTypes[i].getName());
                    methodCode.append(" ");
                    methodCode.append("arg").append(i);
                    // 如果不是最后一个参数，就需要在变量后面加逗号
                    if (i != parameterTypes.length - 1) {
                        methodCode.append(",");
                    }
                }
                methodCode.append(")");
                methodCode.append("{");
                // 需要方法体中的代码
                methodCode.append("org.apache.ibatis.session.SqlSession sqlsession = bank.utils.SqlSessionUtil.openSession();");
                // 需要知道是什么类型的sql语句
                SqlCommandType sqlCommandType = sqlSession.getConfiguration().getMappedStatement().getSqlCommandType();
                if (sqlCommandType == SqlCommandType.SELECT) {

                }

                methodCode.append("}");

                CtMethod ctMethod = CtMethod.make(methodCode.toString(), ctClass);
                ctClass.addMethod(ctMethod);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // 创建对象
        Object obj = null;
        try {
            Class<?> clazz = ctClass.toClass();
            obj = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
