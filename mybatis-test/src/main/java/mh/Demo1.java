package mh;


import mh.bean.User;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

/**
 * 使用直接的配置：
 *
 * @author: mahao
 * @date: 2019/12/4
 */
public class Demo1 {

    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        /**
         * 这一步解析了所有的配置文件，
         * 1.将mybatis主配置文件，解析成了Configuration类；
         * 2.先解析变量标签<properties>,存入到Configuration的variables属性中，是一个Properties
         * 3.解析setting标签
         * 4.给包或者类注册别名，添加到别名注册器typeAliasRegistry的map中
         * 5.解析开发环境和数据源等
         * 6.注册映射处理器，将系统自带的处理器typeHandler注册到类型处理器中typeHandlerRegistry类中，注册
         * 的格式是java类型对应一个处理器map集合，这个集合的key是数据库类型。
         * 7.解析并且封装mapper.xml文件
         * 8.扫描获取mapper.xmlz资源，封装成inputstream
         * 9.注册缓存每个命名空间的缓存处理器
         * 10.解决关联的其他命名空间的缓存处理器，都是存到configuration中，key是命名空间，value是缓存处理器实例
         * 11.解析参数标签，没详细研究，Map<String, ParameterMap> parameterMaps
         * 12.解析resultMap标签，将每一个封装成ResultMap，里面的每一个对应封装成resultMapping，参数有
         * property column  javaType jdbcType和 typeHandler通过这些可以直接将结果集数据映射到java对象中了。
         * 13.解析select|insert|update|delete标签，将他们的属性封装成MappedStatement，这里面含有参数的map，
         * 结果映射的ResultMap(如果类型是ResultType将会自动创建一个statementId + "-Inline"的resultMap)，这个里面含有
         * 了一个语句所有需要的条件，包括参数，语句，结果集映射，包括缓存等等。然后将这个MappedStatement存入到Configuration的
         * Map<String, MappedStatement> mappedStatements 中，key是命名空间+id；
         * 14.解析完成mapper.xml文件后，为命名空间的接口创建生成代理类的工厂类，存入到configuration，与命名空间绑定；
         * 15.解析注解版的mybatis配置文件。
         * 16.配置文件全部解析完成后，生成了configuration实例，通过构建器创建SqlSessionFactory；
         *
         */
        DefaultSqlSessionFactory sqlSessionFactory = (DefaultSqlSessionFactory) new SqlSessionFactoryBuilder().build(inputStream);

        try (DefaultSqlSession session = (DefaultSqlSession) sqlSessionFactory.openSession()) {
            Connection connection = session.getConnection();
            System.out.println(connection);
            User user = (User) session.selectOne("mh.mapper.UserMapper.selectUser", 1);
            System.out.println(user);
            session.commit();
        }
    }


}
