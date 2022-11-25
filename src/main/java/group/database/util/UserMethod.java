package group.database.util;

import group.database.dao.UserMapper;
import group.database.pojo.User;
import org.apache.ibatis.session.SqlSession;

import java.util.Vector;

//方法全部写在这里可以直接调用
public class UserMethod {

    //返回所有的用户（列表形式）
    public static Vector<User> getAllUsers(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);


        Vector<User> allUsers ;
        try {
            allUsers = mapper.getAllUsers();
        }catch (Exception e){
            allUsers=null;
        }finally {
            sqlSession.close();
        }
        return allUsers;
    }

    public static Vector<User> getAllUsersAccordingToType(String type){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Vector<User> allUsers ;
        try {
            allUsers = mapper.getAllUsersAccordingToType(type);
        }catch (Exception e){
            allUsers=null;
        }finally {
            sqlSession.close();
        }
        return allUsers;
    }

    ///通过用户名获取指定用户的分数
    public static  int getScore(String username,String type){
        int score;
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        try {
             score = mapper.getScore(username,type);
        }catch (Exception e){
            //-1说明发生异常
            score=-1;
        }finally {
            sqlSession.close();
        }

        return score;
    }

    //添加新用户
    public static int addUser(User user){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int result;

        try{
            result = mapper.addUser(user);
        }catch (Exception e){
            //-1说明发生异常
            result=-1;
        }finally {
            sqlSession.close();
        }



        //1 代表成功
        return result;
    }

    //更新用户信息
    public static int updateUser(User user){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        int result;

        try{
            result = mapper.updateUser(user);
        }catch (Exception e){
            //-1说明发生异常
            result=-1;
        }finally {
            sqlSession.close();
        }

        //1 成功
        //0 失败
        return result;
    }
}
