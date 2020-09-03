package com.lagou.dao.impl;

import com.lagou.dao.IUserDao;
import com.lagou.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.jnlp.UnavailableServiceException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class IUserDaoImpl implements IUserDao {
    public List<User> findAll() throws IOException {

        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        List<User> users = sqlSession.selectList("userMapper.findAll");

        sqlSession.close();
        return users;
    }

    public User findCondition(User user) {
        return null;
    }
}
