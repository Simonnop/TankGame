package group.Mybatis.dao;


import group.Mybatis.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Vector;

public interface UserMapper {

    //通过用户名获取指定用户的分数
    @Select("select score from tankgame.user where username=#{username}")
    int getScore(@Param("username") String username);

    //获取所有用户
    @Select("select * from tankgame.user  order by score  desc  ")
    Vector<User> getAllUsers();

    //添加新用户
    @Insert("insert into tankgame.user  values(#{username},#{score})")
    int addUser(User user);

    //更新用户信息
    @Update("update tankgame.user set username=#{username},score =#{score} where username=#{username}")
    int updateUser(User user);

    //


}
