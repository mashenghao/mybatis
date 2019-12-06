package mh.mapper;

import mh.bean.User;

/**
 * @author: mahao
 * @date: 2019/12/4
 */
public interface UserMapper {

    User selectUser(int id);

    int insert(User user);
}
