package jdbc;

import mh.bean.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * 自己尝试封装数据库一下操作；
 *
 * @author: mahao
 * @date: 2019/12/9
 */
public class MainClass {

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mybatis", "root", "123456");
        BaseCommand command = new BaseCommand();
        command.setSql("select * from user");
        Executor executor = new SimpleExecutor(connection);
        List<User> query = executor.query(command, command);
        System.out.println(query);
    }
}
