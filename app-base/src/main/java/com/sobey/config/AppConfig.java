package com.sobey.config;

public class AppConfig {

    /******************************rides KEY配置**********************/
    public final static String PWDKEY = "胡强啊胡强原来是个小鸡鸡";
    public final static String DEFAULT_SITECODE = "CBCN";
    public final static Long REDIS_TOKEN_OUT_TIME = 5 * 60L;
    public final static String TOKEN_PREFIX = "APP_TOKEN_";
    public final static String REQ_IP_PREFIX = "REQ_LIMIT_";
    public final static String PHONE_MESSAGE_REGISTE_PREFIX = "PHONE_MESSAGE_REGISTE_";
    public final static String PHONE_MESSAGE_UPDATE_PWD_PREFIX = "PHONE_MESSAGE_UPDATE_PWD_";
    /******************************阿里短信配置**********************/
    public static final String AL_SMS_PRODUCT = "Dysmsapi";
    public static final String AL_SMS_DOMAIN = "dysmsapi.aliyuncs.com";
    public static final String AL_SMS_ACCESSKEYID = "LTAIhkCX6XUdl6R9";
    public static final String AL_SMS_ACCESSKEYSECRET = "ydA5bVkdWVbNj0aARlGcP2yhJ84CEy";
    public static final String AL_SMS_SIGN = "谭帅";
    public static final String TEMPLATECODE= "SMS_126875012";
    public static final Long AL_SMS_OUTTIME = 600L;
    public static final Integer AL_SMS_MESSAGE_CODE_SIZE = 6;

    enum Al_SMS_TEMPLATECODE{
        REGISTE(1 , "SMS_126875012"),
        UPDATE_PWD(2 , "SMS_126875012")
        ;
        private static Al_SMS_TEMPLATECODE sms = null;
        private Integer key;
        private String val;

        Al_SMS_TEMPLATECODE(Integer key, String val) {
            this.key = key;
            this.val = val;
        }
//
//        private Al_SMS_TEMPLATECODE(){}
//        public static Al_SMS_TEMPLATECODE getSms(){
//            if(sms == null){
//               sms = new Al_SMS_TEMPLATECODE();
//            }
//        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }


    }
}
