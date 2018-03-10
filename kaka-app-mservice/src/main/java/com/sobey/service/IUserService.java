package com.sobey.service;

import com.sobey.model.UserBean;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface IUserService {

    public Map<String , Object> login(UserBean userBean) throws Exception;

    public Map<String , Object> registe (UserBean userBean , String code) throws Exception;

    public Map<String , Object> loginOut(HttpServletRequest request) throws Exception;

    public Map<String , Object> updatePaw(Map<String , String> map, String code) throws Exception;

    public Map<String , Object> sendSmsMessage(String phone , Integer type) throws Exception;

}
