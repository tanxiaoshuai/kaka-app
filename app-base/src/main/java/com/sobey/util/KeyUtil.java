package com.sobey.util;

import com.sobey.config.AppConfig;

import java.util.UUID;

public class KeyUtil {

    public static String tokenKey(String userid){
        return new StringBuffer().append(AppConfig.TOKEN_PREFIX).append(userid).toString();
    }

    public static String reqLimitKey(String ip , String packageStr){
        return new StringBuffer().append(AppConfig.REQ_IP_PREFIX).append(ip).append(packageStr).toString();
    }

    public static String phoneMessageRegisteKey(String phone){
        return new StringBuffer().append(AppConfig.PHONE_MESSAGE_REGISTE_PREFIX).append(phone).toString();
    }

    public static String phoneMessageUpdatePwdKey(String phone){
        return new StringBuffer().append(AppConfig.PHONE_MESSAGE_UPDATE_PWD_PREFIX).append(phone).toString();
    }

    public static String phoneMessageUpdatePhoneKey(String phone){
        return new StringBuffer().append(AppConfig.PHONE_MESSAGE_UPDATE_PHONE_PREFIX).append(phone).toString();
    }

    public static String getKey(String str , String prefix){
        return new StringBuffer().append(prefix).append(str).toString();
    }

    public static String uuid(){
        return UUID.randomUUID().toString().replace("-" , "");
    }
}
