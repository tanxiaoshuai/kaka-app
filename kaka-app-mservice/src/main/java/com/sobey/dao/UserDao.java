package com.sobey.dao;
import com.sobey.model.UserBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by TS on 2018/2/25.
 */
@Repository
@Mapper
public interface UserDao extends TemplateDao<UserBean>{

}
