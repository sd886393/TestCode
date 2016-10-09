package com.liji.ssmtest.mapper;

import com.liji.ssmtest.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Jimmy on 2016/9/23.
 */
public interface UserMapper {
    @Select("select * from t_user where user_id = #{userId}")
    User getUser(@Param("userId")String userId);

    @Select("select * from t_user")
    List<User> getAllUsers();

    @Insert("insert into t_user(user_id, user_name,user_age) values (#{userId}, #{userName}, #{userAge})")
    void addUser(@Param("userId")int userId,@Param("userName")String userName, @Param("userAge")String userAge);
}
