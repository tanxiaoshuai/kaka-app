package com.sobey.dao;

import com.sobey.model.JobBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface JobDao extends TemplateDao<JobBean> {
}
