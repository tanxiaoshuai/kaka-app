package com.sobey.config;

import java.util.HashMap;
import java.util.Map;

public class AppConfig {


    /******************************rides KEY配置**********************/
    public final static String PWDKEY = "胡强啊胡强原来是个小鸡鸡";
    public final static String DEFAULT_SITECODE = "CBCN";
    public final static Long REDIS_TOKEN_OUT_TIME = 5 * 60L;
    public final static String TOKEN_PREFIX = "APP_TOKEN_";
    public final static String REQ_IP_PREFIX = "REQ_LIMIT_";
    public final static String PHONE_MESSAGE_REGISTE_PREFIX = "PHONE_MESSAGE_REGISTE_";
    public final static String PHONE_MESSAGE_UPDATE_PWD_PREFIX = "PHONE_MESSAGE_UPDATE_PWD_";
    public final static String PHONE_MESSAGE_UPDATE_PHONE_PREFIX = "PHONE_MESSAGE_UPDATE_PHONE_";
    /******************************阿里短信配置**********************/
    public static Map<Integer , String[]> AL_SMS_TEMPLATECODE;
    static
    {
        AL_SMS_TEMPLATECODE = new HashMap<>();
        AL_SMS_TEMPLATECODE.put(1 , new String[]{"SMS_126875012" , PHONE_MESSAGE_REGISTE_PREFIX});
        AL_SMS_TEMPLATECODE.put(2 , new String[]{"SMS_126875012" , PHONE_MESSAGE_UPDATE_PWD_PREFIX});
        AL_SMS_TEMPLATECODE.put(3 , new String[]{"SMS_126875012" , PHONE_MESSAGE_UPDATE_PHONE_PREFIX});
    }
    public static final String AL_SMS_PRODUCT = "Dysmsapi";
    public static final String AL_SMS_DOMAIN = "dysmsapi.aliyuncs.com";
    public static final String AL_SMS_ACCESSKEYID = "LTAIhkCX6XUdl6R9";
    public static final String AL_SMS_ACCESSKEYSECRET = "ydA5bVkdWVbNj0aARlGcP2yhJ84CEy";
    public static final String AL_SMS_SIGN = "谭帅";
    public static final Long AL_SMS_OUTTIME = 600L;
    public static final Integer AL_SMS_MESSAGE_CODE_SIZE = 6;




}
