package jdbc;

import mh.bean.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: mahao
 * @date: 2019/12/9
 */
public class BaseCommand implements Command, TypeHandler<User> {

    private String sql;
    private Map<Integer, Object> params = new LinkedHashMap<>();

    @Override
    public String getSql() {
        return this.sql;
    }

    @Override
    public Map<Integer, Object> getParams() {
        return this.params;
    }

    @Override
    public boolean putParam(Integer key, Object value) {
        return (null == params.put(key, value));
    }

    @Override
    public void setSql(String sql) {
        this.sql = sql;
    }

    @Override
    public void setParams(Map<Integer, Object> params) {
        this.params = params;
    }

    @Override
    public User conver(ResultSet set) throws SQLException {
        User user = new User();
        user.setId(set.getInt("id"));
        user.setUsername(set.getString("username"));
        user.setSex(set.getString("sex"));
        user.setBirthday(set.getDate("birthday"));
        user.setAddress(set.getString("address"));
        return user;
    }
}
