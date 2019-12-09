package jdbc;


import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @program: mybatis
 * @Date: 2019/12/9 13:33
 * @Author: mahao
 * @Description:
 */
public interface TypeHandler<T> {

    /**
     * 数据库类型转换为java数据类型
     *
     * @param set
     * @return
     */
    T conver(ResultSet set) throws SQLException;
}
