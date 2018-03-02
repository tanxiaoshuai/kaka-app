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

    @Select("SELECT * FROM t_user WHERE phone = #{phone}")
    public UserBean userByPhone(@Param("phone") String phone) throws Exception;

}
