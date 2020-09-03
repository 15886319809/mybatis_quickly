package com.lagou.test;

import com.lagou.dao.IUserDao;
import com.lagou.dao.impl.IUserDaoImpl;
import com.lagou.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionException;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
        sqlSession.insert("com.lagou.dao.IUserDao.insertUser",user);
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

        sqlSession.delete("com.lagou.dao.IUserDao.deleteUser",1);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test1() throws IOException {
        IUserDao userDao = new IUserDaoImpl();
        List<User> all = userDao.findAll();
        for (User user : all) {
            System.out.println(user);
        }
    }


    @Test
    public void test2() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        List<User> all = mapper.findAll();
        for (User user : all) {
            System.out.println(user);
        }
    }




    @Test
    public void test3() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        User user = new User();
        user.setUsername("张三");
        User all = mapper.findCondition(user);
        System.out.println(all);
    }

    @Test
    public void test4() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        User user = new User();
        user.setId(1);
        User user1 = new User();
        user1.setId(3);
        List<User>list = new ArrayList<User>();
        list.add(user);
        list.add(user1);
        List<User> userByIds = mapper.findUserByIds(list);
        for (User userById : userByIds) {
            System.out.println(userById);
        }
    }
}
