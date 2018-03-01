package com.sobey.dao;

import com.sobey.model.SiteBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SiteDao extends TemplateDao<SiteBean>{

    @Select("select s.* from t_user_site us left join t_site s on us.siteid = s.siteid where userid = #{userid}")
    public List<SiteBean> findByIdList(String userid) throws Exception;

}
