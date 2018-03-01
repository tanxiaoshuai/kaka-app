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
    @Results({
            @Result(property = "userid",column = "userid"),
            @Result(property = "sitelist" , column = "userid" , many = @Many(select = "com.sobey.dao.SiteDao.findByIdList"))
    })
    public UserBean userByPhone(@Param("phone") String phone) throws Exception;

}
