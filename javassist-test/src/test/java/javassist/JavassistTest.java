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
        CtClass ctClass = pool.makeClass("bank.dao,impl.AccountDaoImpl");
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
                String methodName = method.getName();
                methodCode.append("public ").append(method.getReturnType().getName()).append(" ").append(methodName).append("(");
                // 2.获取方法的参数
                Class<?>[] parameterTypes = method.getParameterTypes();
                for (int i = 0; i < parameterTypes.length; i++) {
                    if (i!= 0) {
                        methodCode.append(",");
                    }
                    methodCode.append(parameterTypes[i].getName());
                }
                methodCode.append("){");
                // 3.获取方法的返回值
                Class<?> returnType = method.getReturnType();
                if (returnType!= void.class) {
                    methodCode.append("System.out.println(\"hello ");
                    methodCode.append(methodName);
                    methodCode.append("\");");
                }
                methodCode.append("return null;}");
                CtMethod ctMethod = CtMethod.make(methodCode.toString(), ctClass);
                ctClass.addMethod(ctMethod);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        // 在内存中生成class,并且加载到JVM中
        Class<?> clazz = ctClass.toClass();
        // 创建对象
        AccountDao accountDao = (AccountDao)clazz.newInstance();
        // 调用方法
        accountDao.delete();
        accountDao.insert("123456");
        accountDao.update("123456", 100.0);
        accountDao.selectByActno("123456");
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
        // 创建对象
        AccountDao obj = (AccountDao)clazz.newInstance();
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
        String methodCode = "public void insert(){System.out.println(123);}";
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
