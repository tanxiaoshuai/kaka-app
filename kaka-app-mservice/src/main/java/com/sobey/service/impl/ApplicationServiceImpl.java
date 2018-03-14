package com.sobey.service.impl;

import com.sobey.config.AppConfig;
import com.sobey.config.StatusConfig;
import com.sobey.dao.ApplicationDao;
import com.sobey.model.ApplicationBean;
import com.sobey.service.IApplicationService;
import com.sobey.util.ParamValidateUtil;
import com.sobey.util.ResultUtil;
import com.sobey.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by TS on 2018/3/14.
 */
@Service
public class ApplicationServiceImpl implements IApplicationService {

    @Autowired
    private ApplicationDao applicationDao;

    @Override
    public Map<String, Object> findByApplicationList(HttpServletRequest request) throws Exception {
        String sitecode = request.getHeader("sitecode");
        String token = request.getHeader("token");
        ParamValidateUtil.notNull(sitecode , "站点不能为空");
        List<ApplicationBean> list = applicationDao.findBySQLRequireToList("sitecode = '"+ AppConfig.DEFAULT_SITECODE +"' and status = "+StatusConfig.APPLICATION_LINE , ApplicationBean.class);
        if(AppConfig.DEFAULT_SITECODE.equals(sitecode))
            return ResultUtil.success(list);
        List<ApplicationBean> lists = applicationDao.findBySiteCodeAndUserIdAndStatusList(sitecode , TokenUtil.tokenToIdAndKey(token)[0] , StatusConfig.APPLICATION_LINE);
        if(lists.size() == 0)
            return ResultUtil.success(list);
        List<ApplicationBean> listAll = new ArrayList<>();
        listAll.addAll(list);
        listAll.addAll(lists);
        return ResultUtil.success(listAll);
    }
}
