package com.sobey.util;
import com.sobey.config.AppConfig;
import com.sobey.config.ResultInfo;
import com.sobey.exception.FinalException;
import com.sobey.model.UserBean;
import com.sobey.redis.RedisUtil;
import org.springframework.util.Base64Utils;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TS on 2018/2/28.
 */
public class TokenUtil {

    public static String createToken(String userid  ,long logintime, String phoneid){
        StringBuffer str = new StringBuffer();
        str.append(KeyUtil.tokenKey(userid)).append("&").append(logintime).append("&").append(phoneid);
        System.out.println(str.toString());
        return Base64Utils.encodeToString(str.toString().getBytes());
    }

    public static List tokenParam(String token){
        if(RegexUtil.isNull(token))
            throw new FinalException(ResultInfo.NOAUTHORIZE);
        List list = new ArrayList();
        token = new String(Base64Utils.decodeFromString(token));
        Object [] objects = token.split("&");
        for(Object o : objects)
            list.add(o);
        return list;
    }

    public static boolean checkToken(String token){
        List list = tokenParam(token);
        RedisUtil redisUtil = BeanFactoryUtil.getBeanByClass(RedisUtil.class);
        redisUtil.setExpire((String) list.get(0), AppConfig.REDIS_TOKEN_OUT_TIME);
        if(!redisUtil.exists((String) list.get(0)))
            throw new FinalException(ResultInfo.LOGINOUTTIME);
        UserBean user = (UserBean) redisUtil.get((String) list.get(0));
        if(!user.getToken().equals(token)){
            if (!user.getDeviceId().equals(list.get(2)))
                throw new FinalException(ResultInfo.ANOTHERdDEVICELOGIN);
            else
                throw new FinalException(ResultInfo.TOKENCHECKERROR);
        }
        return true;
    }

    public static String [] tokenToIdAndKey(String token){
        List list = tokenParam(token);
        String key = (String)list.get(0);
        String [] s = key.split("_");
        return new String[]{s[s.length - 1] , key};
    }
}
