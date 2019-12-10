package mh;

import com.baomidou.mybatisplus.MybatisSessionFactoryBuilder;
import mh.bean.User;
import mh.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.InputStream;

/**
 * @author: mahao
 * @date: 2019/12/10
 */
public class Demo1 {

    public static void main(String[] args) {
        InputStream inputStream = Demo1.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new MybatisSessionFactoryBuilder().build(inputStream);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user = mapper.selectById(1);
            System.out.println(user);
        }
    }
}
