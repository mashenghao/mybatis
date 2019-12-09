package mh;

import mh.bean.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * 测试mapper配置文件中的主键返回
 *
 * @author: mahao
 * @date: 2019/12/5
 */
public class Demo4 {


    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            User user = new User();
            user.setAddress("hanan");
            user.setBirthday(new Date());
            user.setSex("m");
            user.setUsername("zzz2");
            int i = session.insert("mh.mapper.UserMapper.insert", user);
            System.out.println(user);
            session.commit();
        }
    }
}
