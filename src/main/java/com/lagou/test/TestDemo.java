package com.lagou.test;

import com.lagou.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestDemo {


    @Test
    public void testFindAll() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        List<User> users = sqlSession.selectList("userMapper.findAll");
        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i));
        }
        sqlSession.close();
    }

    @Test
    public void testAddUser() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        User user = new User();
        user.setId(3);
        user.setUsername("王五");
        sqlSession.insert("userMapper.insertUser",user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testUpdateUser() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        User user = new User();
        user.setId(3);
        user.setUsername("jack");
        sqlSession.update("userMapper.updateUser",user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testDeleteUser() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();

        sqlSession.update("userMapper.deleteUser",3);
        sqlSession.commit();
        sqlSession.close();
    }

}
