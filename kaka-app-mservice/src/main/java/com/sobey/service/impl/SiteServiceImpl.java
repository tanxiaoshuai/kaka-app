package com.sobey.service.impl;

import com.sobey.dao.SiteDao;
import com.sobey.model.SiteBean;
import com.sobey.service.ISiteService;
import com.sobey.util.ParamValidateUtil;
import com.sobey.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SiteServiceImpl implements ISiteService{

    @Autowired
    private SiteDao siteDao;

    @Override
    public Map<String, Object> findByIdList(String userid) throws Exception {
        ParamValidateUtil.notNull(userid , "用户id不能为空");
        List<SiteBean> list = siteDao.findByIdList(userid);
        return ResultUtil.success(list);
    }

    @Override
    public Map<String, Object> findByList() throws Exception {
        List<SiteBean> list = siteDao.findByList(SiteBean.class);
        return ResultUtil.success(list);
    }

    @Override
    public Map<String, Object> findBySiteNameToList(String sitename) throws Exception {
        List<SiteBean> list = siteDao.findBySQLRequireToList("sitename like '%"+ sitename +"%'" , SiteBean.class);
        return ResultUtil.success(list);
    }
}
