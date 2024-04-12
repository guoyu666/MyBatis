package javassist;

import bank.dao.AccountDao;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

public class JavassistTest {
    @Test
    public void testGenerateAccountDaoImpl() throws Exception {
        // 获取类池
        ClassPool pool = ClassPool.getDefault();
        // 制造类
        CtClass ctClass = pool.makeClass("bank.dao.impl.AccountDaoImpl");
        // 制造接口
        CtClass ctInterface = pool.makeInterface("bank.dao.AccountDao");
        // 添加接口到类中
        ctClass.addInterface(ctInterface);
        // 实现接口中的所有方法
        // 获取接口中的所有方法
        Method[] methods = AccountDao.class.getDeclaredMethods();
        Arrays.stream(methods).forEach(method -> {
            // method是接口中的抽象方法
            // 把method抽象方法给实现了
            try {
                StringBuilder methodCode = new StringBuilder();
                // 1.获取方法的名称
                methodCode.append("public ");   // 追加修饰符列表
                methodCode.append(method.getReturnType().getName());    // 追加返回值类型
                methodCode.append(" ");
                methodCode.append(method.getName());    //  追加方法名
                methodCode.append("(");
                // 2.获取方法的参数
                Class<?>[] parameterTypes = method.getParameterTypes();
                for (int i = 0; i < parameterTypes.length; i++) {
                    Class<?> parameterType = parameterTypes[i];
                    methodCode.append(parameterType.getName()); // 追加参数的类型
                    methodCode.append(" ").append("arg" + i);   // 追加变量名
                    // 如果不是最后一个参数，就需要在变量后面加逗号
                    if (i != parameterTypes.length - 1) {
                        methodCode.append(",");
                    }
                }
                methodCode.append("){System.out.println(11111); ");
                // 3.动态的添加return语句
                String returnTypeSimpleName = method.getReturnType().getSimpleName();
                if ("void".equals(returnTypeSimpleName)) {

                } else if ("int".equals(returnTypeSimpleName)) {
                    methodCode.append("return 1;");
                } else if ("String".equals(returnTypeSimpleName)) {
                    methodCode.append("return \"hello\";");
                }
                methodCode.append("}");
                System.out.println(methodCode);
                CtMethod ctMethod = CtMethod.make(methodCode.toString(), ctClass);
                ctClass.addMethod(ctMethod);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        // 在内存中生成class,并且加载到JVM中
        Class<?> clazz = ctClass.toClass();
        // 创建对象
        AccountDao accountDao = (AccountDao) clazz.newInstance();
        // 调用方法
        accountDao.delete();
        accountDao.update("aaa", 1000.0);
        accountDao.selectByActno("aaa");
    }

    @Test
    public void testGenerateImpl() throws Exception {
        // 获取类池，这个类池就是用来给我生成class的
        ClassPool pool = ClassPool.getDefault();
        // 制造类（需要告诉javassist类名是啥）
        CtClass ctClass = pool.makeClass("bank.dao.impl.AccountDaoImpl");
        // 制造接口
        CtClass ctInterface = pool.makeInterface("bank.dao.AccountDao");
        // 添加接口到类中
        ctClass.addInterface(ctInterface);
        // 实现接口中的方法（先制造一个方法）
        // 制造方法
        CtMethod ctMethod = CtMethod.make("public void delete(){System.out.println(\"hello delete\");}", ctClass);
        // 将方法添加到类中
        ctClass.addMethod(ctMethod);

        // 在内存中生成类，同时将生成的类加载到JVM中
        Class<?> clazz = ctClass.toClass();
        // 创建对象(强制类型转换)
        AccountDao obj = (AccountDao) clazz.newInstance();
        // 获取AccountDaoImpl中的delete方法
        Method deleteMethod = clazz.getDeclaredMethod("delete");
        // 调用delete方法
        deleteMethod.invoke(obj);
    }

    @Test
    public void testGenerateFirstClass() throws Exception {
        // 获取类池，这个类池就是用来给我生成class的
        ClassPool pool = ClassPool.getDefault();
        // 制造类（需要告诉javassist类名是啥）
        CtClass ctClass = pool.makeClass("bank.dao.impl.AccountDaoImpl");
        // 制造方法
        String methodCode = "public void insert(){System.out.println(\"Hello World\");}";
        CtMethod ctMethod = CtMethod.make(methodCode, ctClass);
        // 添加方法到类中
        ctClass.addMethod(ctMethod);
        // 在内存中生成class
        ctClass.toClass();

        // 类加载到JVM中，返回AccountDaoImpl类的字节码
        Class<?> clazz = Class.forName("bank.dao.impl.AccountDaoImpl");
        // 创建对象
        Object obj = clazz.newInstance();
        // 获取AccountDaoImpl中的insert方法
        Method insertMethod = clazz.getDeclaredMethod("insert");
        // 调用insert方法
        insertMethod.invoke(obj);
    }
}
