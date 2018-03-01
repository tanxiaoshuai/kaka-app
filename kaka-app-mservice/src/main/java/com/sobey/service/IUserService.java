package com.sobey.service;

import com.sobey.model.UserBean;

import java.util.Map;

public interface IUserService {

    public Map<String , Object> login(UserBean userBean) throws Exception;

    public Map<String , Object> registe (UserBean userBean , String code) throws Exception;

}
