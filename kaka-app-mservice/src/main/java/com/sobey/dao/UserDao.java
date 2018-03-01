package com.sobey.dao;
import com.sobey.model.UserBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by TS on 2018/2/25.
 */
@Repository
@Mapper
public interface UserDao extends TemplateDao<UserBean>{

    @Select("SELECT u.*, s.sitename AS sitename , s.sitecode AS sitecode,j.jobname AS jobname,d.departmentname AS departmentname FROM t_user_site us LEFT JOIN t_user u ON  us.userid = u.userid LEFT JOIN t_site s ON us.siteid = s.siteid LEFT JOIN t_job j ON j.jobid = us.jobid LEFT JOIN t_department d ON us.departmentid = d.departmentid WHERE u.phone = #{phone}")
    public UserBean userByPhone(@Param("phone") String phone) throws Exception;

}
