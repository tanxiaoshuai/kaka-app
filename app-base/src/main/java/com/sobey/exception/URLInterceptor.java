package com.sobey.exception;

import com.alibaba.fastjson.JSONObject;
import com.sobey.util.BeanFactoryUtil;
import com.sobey.util.RedisUtil;
import com.sobey.util.RegexUtil;
import com.sobey.util.TokenUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 登陆拦截器
 * Created by TS on 2017/7/9.
 */
@Component
public class URLInterceptor implements HandlerInterceptor {

    private Logger logger = Logger.getLogger(URLInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String reqUrl = httpServletRequest.getRequestURL().toString().trim();
        logger.info("当前请求的地址：["+ reqUrl +"]");
        return false;
    }

    //请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    //在整个请求结束之后被调用
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    public static void checkToken(String token){
        if(RegexUtil.isNull(token))
            throw new FinalException("0002" , "未授权失败");
        List list = TokenUtil.tokenParam(token);
        RedisUtil redisUtil = BeanFactoryUtil.getBeanByClass(RedisUtil.class);
        if(!redisUtil.exists((String) list.get(0)))
            throw new FinalException("0003" , "登录失效");
        long s = System.currentTimeMillis();
        Object o =redisUtil.get((String) list.get(0));
        System.out.println("redis查询时间" + (System.currentTimeMillis() - s));
        JSONObject user = (JSONObject) JSONObject.toJSON(o);
        System.out.println("json转换时间" + (System.currentTimeMillis() - s));
        if(!user.get("token").equals(token)){
            if (!user.get("deviceId").equals(list.get(2)))
                throw new FinalException("0004" , "用户在其他设备上登录");
            else
                throw new FinalException("0002" , "未授权失败");
        }
    }
}
