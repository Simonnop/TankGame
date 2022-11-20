package group.Mybatis.dao;


import group.Mybatis.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Vector;

public interface UserMapper {

    //通过用户名获取指定用户的分数
    @Select("select score from tankgame.user where username=#{username} and type=#{type}")
    int getScore(@Param("username") String username,@Param("type") String type);

    //获取所有用户
    @Select("select * from tankgame.user  order by score  desc  ")
    Vector<User> getAllUsers();

    //根据类型获取所有的用户
    @Select("select * from tankgame.user  where type=#{type} order by score  desc  ")
    Vector<User> getAllUsersAccordingToType(@Param("type") String type);

    //添加新用户
    @Insert("insert into tankgame.user  values(#{username},#{score},#{type})")
    int addUser(User user);

    //更新用户信息
    @Update("update tankgame.user set username=#{username},score =#{score},type=#{type} where username=#{username} and type=#{type}")
    int updateUser(User user);

    //


}
