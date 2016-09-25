package com.liji.ssmtest.mapper;

import com.liji.ssmtest.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by Jimmy on 2016/9/23.
 */
public interface UserMapper {
    @Select("select * from t_user where user_id = #{userId}")
    User getUser(@Param("userId")String userId);
}
