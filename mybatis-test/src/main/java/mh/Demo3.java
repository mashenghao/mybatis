package mh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 原装使用
 *
 * @author: mahao
 * @date: 2019/12/4
 */
public class Demo3 {

    public static void main(String[] args) {
        try {
            /**
             * spi服务，会自动去加载类；
             */
           // Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mybatis", "root", "123456");
            PreparedStatement ps = connection.prepareStatement("select * from USER where id = ?");
            ps.setInt(1, 1);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String username = rs.getString("username");
                System.out.println(username);
            }

        } catch (Exception e) {
        }
    }
}
