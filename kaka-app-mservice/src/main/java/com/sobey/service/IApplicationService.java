package com.sobey.service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by TS on 2018/3/14.
 */
public interface IApplicationService {

    public Map<String , Object> findByApplicationList(HttpServletRequest request) throws Exception;
}
