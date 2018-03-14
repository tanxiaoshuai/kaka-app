package com.sobey.dao;

import com.sobey.model.ApplicationBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by TS on 2018/3/14.
 */
@Repository
@Mapper
public interface ApplicationDao extends TemplateDao<ApplicationBean> {

    /**
     * 根据站点 用户id 应用状态查询相关列表
     * @param sitecode
     * @param userid
     * @param status
     * @return
     * @throws Exception
     */
    @Select("SELECT ap.* FROM t_application_user au JOIN t_application ap ON ap.applicationid = au.applicationid WHERE userid= #{userid} AND sitecode = #{sitecode} AND status = #{status}")
    public List<ApplicationBean> findBySiteCodeAndUserIdAndStatusList(@Param("sitecode") String sitecode , @Param("userid") String userid , @Param("status") Integer status) throws Exception;

}
