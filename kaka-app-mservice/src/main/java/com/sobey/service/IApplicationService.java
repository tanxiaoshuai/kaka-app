package com.sobey.service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by TS on 2018/3/14.
 */
public interface IApplicationService {

    /**
     * 根据sitecode 和 用户 id 查询应用
     * @param request
     * @return
     * @throws Exception
     */
    public Map<String , Object> findByApplicationList(HttpServletRequest request) throws Exception;

    /**
     * 查询相应站点应用
     * @param request
     * @return
     * @throws Exception
     */
    public Map<String , Object> findBySiteCodeList(HttpServletRequest request) throws Exception;
}
