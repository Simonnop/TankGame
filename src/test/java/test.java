import group.Mybatis.dao.UserMapper;
import group.Mybatis.pojo.User;
import group.Mybatis.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Vector;

public class test {

    @Test
    public void test(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Vector<User> allUsers = mapper.getAllUsers();
        System.out.println(allUsers.toString());

        sqlSession.close();
    }
    @Test
    public void test1(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        int score = mapper.getScore("ziyu");
        System.out.println(score);
        sqlSession.close();
    }

    @Test
    public void test2(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        int popo = mapper.addUser(new User("popo"));
        System.out.println(popo);
        sqlSession.close();
    }

    @Test
    public void test3(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        int laozi = mapper.updateUser(new User("ziyu",12));
        System.out.println(laozi);
        sqlSession.close();
    }
}
