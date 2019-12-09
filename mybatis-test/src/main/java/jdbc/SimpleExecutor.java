package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: mahao
 * @date: 2019/12/9
 */
public class SimpleExecutor implements Executor {

    Connection conn;

    public SimpleExecutor(Connection conn) {
        this.conn = conn;
    }

    @Override
    public <T> List<T> query(Command command, TypeHandler<T> handler) {
        try {
            PreparedStatement ps = initPrepare(command);
            ResultSet set = ps.executeQuery();
            List<T> list = new ArrayList<>();
            while (set.next()) {
                T conver = handler.conver(set);
                list.add(conver);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private PreparedStatement initPrepare(Command command) throws SQLException {
        String sql = command.getSql();
        PreparedStatement ps = conn.prepareStatement(sql);
        Map<Integer, Object> params = command.getParams();
        Set<Map.Entry<Integer, Object>> entries = params.entrySet();
        for (Map.Entry<Integer, Object> entry : entries) {
            ps.setObject(entry.getKey(), entry.getValue());
        }
        return ps;
    }

}
