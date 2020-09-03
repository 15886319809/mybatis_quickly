package com.lagou.dao;

import com.lagou.pojo.User;

import java.io.IOException;
import java.util.List;

public interface IUserDao {
    public List<User>findAll() throws IOException;

    public User findCondition(User user);


    public List<User>findUserByIds(List<User> users);
}
