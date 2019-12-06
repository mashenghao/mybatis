package mh;

import mh.bean.User;
import mh.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * 使用代理类
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
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user = mapper.selectUser(1);

            System.out.println(user);
        }
    }

}
