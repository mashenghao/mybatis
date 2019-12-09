package jdbc;

import java.util.List;

/**
 * @program: mybatis
 * @Date: 2019/12/9 13:30
 * @Author: mahao
 * @Description:
 */
public interface Executor {

    /**
     * 查询操作;
     *
     * @param command
     * @param handler
     * @param <T>
     * @return
     */
    <T> List<T> query(Command command, TypeHandler<T> handler);
}
