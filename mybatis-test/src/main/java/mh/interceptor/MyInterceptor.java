package mh.interceptor;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;

import java.util.Properties;

/**
 * MyBatis 允许你在已映射语句执行过程中的某一点进行拦截调用。
 * 默认情况下，MyBatis 允许使用插件来拦截的方法调用包括：
 *
 * @author: mahao
 * @date: 2019/12/9
 */
public class MyInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        System.out.println(target);
        return target;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
