package com.sobey.dao;

import com.sobey.model.SiteBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SiteDao extends TemplateDao<SiteBean>{

}
