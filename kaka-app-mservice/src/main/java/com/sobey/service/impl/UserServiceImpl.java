package com.sobey.service.impl;

import com.alibaba.fastjson.JSONObject;
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

import javax.servlet.http.HttpServletRequest;
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
        ParamValidateUtil.notNull(userBean.getLoginname() , "用户名不能为空");
        ParamValidateUtil.notNull(userBean.getPwd() , "密码不能为空");
        UserBean user = userDao.userByPhone(userBean.getLoginname());
        if(user == null)
            throw new FinalException(ResultInfo.USER_ISNULL);
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
        redisUtil.set(TokenUtil.tokenKey(user.getUserid()) , user , AppConfig.REDIS_TOKEN_OUT_TIME);
        return ResultUtil.success(JSONObject.toJSON(user));
    }

    @Override
    public Map<String, Object> registe(UserBean userBean , String code) throws Exception {
        ParamValidateUtil.phone(userBean.getPhone());
        UserBean user = userDao.findBySQLRequireToBean("phone = '"+userBean.getPhone()+"'" , UserBean.class);
        if(user != null)
            throw new FinalException(ResultInfo.PHONE_ISNOTNULL);
        ParamValidateUtil.password(userBean.getPwd());
        ParamValidateUtil.nickname(userBean.getNickname());
        user = userDao.findBySQLRequireToBean("nickname = '" + userBean.getNickname() + "'" , UserBean.class);
        if(user != null)
            throw new FinalException(ResultInfo.NICKNAME_ISNOTNULL);
        ParamValidateUtil.username(userBean.getUsername());

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

    @Override
    public Map<String, Object> loginOut(HttpServletRequest request) throws Exception {
        String [] param = TokenUtil.tokenToIdAndKey(request.getHeader("token"));
        UserBean userBean = new UserBean();
        userBean.setUserid(param[0]);
        userBean.setLoginstatus(0);
        userDao.updateById(userBean);
        redisUtil.remove(param[1]);
        return ResultUtil.success();
    }

    @Override
    public Map<String, Object> updatePaw(Map<String, String> map) throws Exception {
        String phone = map.get("phone");
        String newpwd = map.get("newpwd");
        String code = map.get("code");
        ParamValidateUtil.phone(phone);
        ParamValidateUtil.password(newpwd);
        userDao.updateBySQLRequire("pwd = '" + newpwd +"' where phone = '" + phone + "'" , UserBean.class );
        return ResultUtil.success();
    }
}
