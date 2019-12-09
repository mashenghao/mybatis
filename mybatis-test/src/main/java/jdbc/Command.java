package jdbc;

import java.util.Map;

/**
 * @program: mybatis
 * @Date: 2019/12/9 13:30
 * @Author: mahao
 * @Description: 语句命令
 */
public interface Command {

    String getSql();

    public void setSql(String sql);

    Map<Integer, Object> getParams();

    void setParams(Map<Integer, Object> params);

    boolean putParam(Integer key, Object value);
}
