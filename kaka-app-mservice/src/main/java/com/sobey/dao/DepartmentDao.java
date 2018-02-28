package com.sobey.dao;

import com.sobey.model.DepartmentBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DepartmentDao extends TemplateDao<DepartmentBean>{
}
