package com.sobey.log;
import com.alibaba.fastjson.JSONObject;
import com.sobey.config.AppConfig;
import com.sobey.exception.FinalException;
import com.sobey.redis.RedisUtil;
import com.sobey.util.BeanFactoryUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Aspect
@Component
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.sobey.controller.*.*(..))")
    public void webLog() {}

    @Before("@annotation(limit)")
    public void requestLimit(JoinPoint joinPoint , RequestLimit limit){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        StringBuffer key = new StringBuffer().append(AppConfig.REQ_IP_PREFIX).append(request.getLocalAddr());
        RedisUtil redisUtil = BeanFactoryUtil.getBeanByClass(RedisUtil.class);
        Integer count = 0;
        if(redisUtil.exists(key.toString()))
            count = (Integer) redisUtil.get(key.toString());
        else
            redisUtil.set(key.toString() , count , limit.time());
        if(count >= limit.count())
            throw new FinalException("0010" , "超出访问次数");
        else
            redisUtil.set(key.toString() , count + 1 , redisUtil.getExpire(key.toString()));
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws IOException {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("url ={}",request.getRequestURL().toString().trim());
        logger.info("method={}",request.getMethod());
        logger.info("ip={}",request.getLocalAddr());
        logger.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName()+'.'+ joinPoint.getSignature().getName());
        logger.info("args={}",joinPoint.getArgs());
    }

    @AfterReturning(returning = "o" , pointcut = "webLog()")
    public void doAfterReturning(Object o) {
        logger.info("response={}",o.toString());
    }
}
