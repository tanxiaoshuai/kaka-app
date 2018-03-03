package com.sobey.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sobey.dao.UserDao;
import com.sobey.model.UserBean;
import com.sobey.redis.IRedisListenerService;
import com.sobey.util.BeanFactoryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by TS on 2018/3/3.
 */
@Service
public class RedisListenerServiceImpl implements IRedisListenerService{

    @Autowired
    private UserDao userDao;

    @Override
    public void redisOnMessage(String key) throws Exception {
        UserBean userBean = BeanFactoryUtil.getBeanByClass(UserBean.class);
        userBean.setUserid(key);
        userBean.setLoginstatus(0);
        userDao.updateById(userBean);
    }
}
