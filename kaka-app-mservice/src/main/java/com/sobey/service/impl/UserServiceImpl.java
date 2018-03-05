package com.sobey.service.impl;

import com.sobey.config.AppConfig;
import com.sobey.config.ResultInfo;
import com.sobey.dao.UserDao;
import com.sobey.exception.FinalException;
import com.sobey.model.UserBean;
import com.sobey.redis.RedisUtil;
import com.sobey.util.ResultUtil;
import com.sobey.service.IUserService;
import com.sobey.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    @Transactional
    public Map<String, Object> login(UserBean userBean) throws Exception {
        ParamValidateUtil.notNull(userBean.getPhone() , "电话号码不能为空");
        ParamValidateUtil.notNull(userBean.getPwd() , "密码不能为空");
        UserBean user = userDao.userByPhone(userBean.getPhone());
        if(user == null)
            throw new FinalException(ResultInfo.USERISNULL);
        if(!userBean.getPwd().equals(user.getPwd()))
            throw new FinalException(ResultInfo.PASSWORDERROR);
        long s = System.currentTimeMillis();
        String token = TokenUtil.createToken(user.getUserid() , s , userBean.getDeviceId());
        user.setDeviceId(userBean.getDeviceId());
        user.setAppversion(userBean.getAppversion());
        user.setDevicetype(userBean.getDevicetype());
        user.setDevicemodel(userBean.getDevicemodel());
        user.setLoginnumber(user.getLoginnumber() + 1);
        user.setLastlogintime(DateUtil.longForTime(s , DateUtil.YEARTOSS));
        user.setToken(token);
        user.setLoginstatus(1);
        userBean = new UserBean();
        userBean.setUserid(user.getUserid());
        userBean.setAppversion(user.getAppversion());
        userBean.setDeviceId(user.getDeviceId());
        userBean.setDevicetype(user.getDevicetype());
        userBean.setDevicemodel(user.getDevicemodel());
        userBean.setLoginstatus(user.getLoginstatus());
        userBean.setLoginnumber(user.getLoginnumber());
        userBean.setLastlogintime(user.getLastlogintime());
        userDao.updateById(userBean);
        redisUtil.set(AppConfig.TOKEN_PREFIX + user.getUserid() , user , AppConfig.REDIS_OUT_TIME);
        return ResultUtil.success(user);
    }

    @Override
    public Map<String, Object> registe(UserBean userBean , String code) throws Exception {
        ParamValidateUtil.notNull(userBean.getPhone() , "电话号码不能为空");
        ParamValidateUtil.min(6 , userBean.getPhone() , "手机号必须是11位");
        ParamValidateUtil.phone(userBean.getPhone() , "手机号格式错误");
        ParamValidateUtil.notNull(userBean.getPwd() , "密码不能为空");
        ParamValidateUtil.min(6,userBean.getPwd() , "密码至少6位数");
        UserBean user = userDao.findBySQLRequireToBean("phone = '"+userBean.getPhone()+"'" , UserBean.class);
        if(user != null)
            throw new FinalException(ResultInfo.USERISNOTNULL);
        userBean.setUserid(UUID.randomUUID().toString().replace("-" , ""));
        userBean.setPwd(MD5Util.getPwd(AppConfig.PWDKEY+userBean.getPwd()));
        userBean.setRegisttime(DateUtil.longForTime(System.currentTimeMillis() , DateUtil.YEARTOSS));
        userBean.setLastlogintime(DateUtil.longForTime(System.currentTimeMillis() , DateUtil.YEARTOSS));
        userBean.setLoginnumber(0L);
        userBean.setLoginstatus(0);
        userBean.setStatus(0);
        userBean.setSitecode(AppConfig.DEFAULT_SITECODE);
        userDao.save(userBean);
        return ResultUtil.success();
    }
}
