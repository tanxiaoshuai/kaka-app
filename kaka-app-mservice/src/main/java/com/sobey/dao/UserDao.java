package com.sobey.dao;
import com.sobey.model.UserBean;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * Created by TS on 2018/2/25.
 */
@Repository
@Mapper
public interface UserDao extends TemplateDao<UserBean>{

    @Select("SELECT * FROM t_user WHERE phone = #{loginname}")
    public UserBean userByPhone(@Param("loginname") String loginname) throws Exception;

    @Select("SELECT * FROM t_user WHERE nickname = #{loginname}")
    public UserBean userByName(@Param("loginname") String loginname) throws Exception;

}
