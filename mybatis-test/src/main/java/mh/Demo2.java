package mh;

import mh.bean.User;
import mh.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSession;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * 使用代理类；
 * 分析如何创建代理类的，以及如何获取到MappedStatement的
 *
 * @author: mahao
 * @date: 2019/12/4
 */
public class Demo2 {

    SqlSessionFactory sqlSessionFactory = null;

    @Before
    public void init() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void test2() {
        //System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        try (DefaultSqlSession session = (DefaultSqlSession) sqlSessionFactory.openSession()) {
            /**
             *1.UserMapper是代理生成的；解析mapper.xml文件的时候，将mapper接口封装成了MapperFactory，里面
             * 存储了mapper的接口信息。实现方式是jdk的动态代理，代理的执行逻辑是MapperProxy执行的。
             *2.会判断执行的方法，是否是要被代理的方法，如果不是，直接执行；
             * 3.是mapper中要执行的方法，将会调用mapperMethod.execute(sqlSession, args)的方法。
             * 4.mapperMethod如果是第一次请求，mapperMethod =
             *              new MapperMethod(mapperInterface, method, sqlSession.getConfiguration());将会创建。
             * 5.执行的时候，是使用execute()方法，会根据接口名+方法名组成mappedId，去获取MappedStatement执行。
             */
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user = mapper.selectUser(1);

            System.out.println(user);
        }
    }

}
