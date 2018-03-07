package com.sobey.util;
import com.sobey.config.RegexConfig;
import com.sobey.config.ResultInfo;
import com.sobey.exception.FinalException;

/**
 * Created by TS on 2018/2/24.
 */
public class ParamValidateUtil {

    public static void notNull(String param , String msg){

        if(RegexUtil.isNull(param))
            throw new FinalException(ResultInfo.ERROR_PARAM.setMsg(msg));
    }

    public static void length(int min , long max , String param , String msg){
        long len = param.length();
        if (len < min || len > max)
            throw new FinalException(ResultInfo.ERROR_PARAM.setMsg(msg));
    }

    public static void min(int min , Object param, String msg){
        if(param instanceof String)
            if (((String) param).length() < min)
                throw new FinalException(ResultInfo.ERROR_PARAM.setMsg(msg));
        if(param instanceof Integer)
            if ((Integer)param < min)
                throw new FinalException(ResultInfo.ERROR_PARAM.setMsg(msg));
        if(param instanceof Long)
            if ((Long)param < min)
                throw new FinalException(ResultInfo.ERROR_PARAM.setMsg(msg));
    }

    public static void max(int max , Object param, String msg){
        if(param instanceof String)
            if (((String) param).length() > max)
                throw new FinalException(ResultInfo.ERROR_PARAM.setMsg(msg));
        if(param instanceof Integer)
            if ((Integer)param > max)
                throw new FinalException(ResultInfo.ERROR_PARAM.setMsg(msg));
        if(param instanceof Long)
            if ((Long)param > max)
                throw new FinalException(ResultInfo.ERROR_PARAM.setMsg(msg));
    }

    public static void pattern(String param , String reg , String msg){
        if(!RegexUtil.match(param , reg))
            throw new FinalException(ResultInfo.ERROR_PARAM.setMsg(msg));
    }

    //----------------------------------------业务相关--------------------------------------------//

    public static void email (String em , String msg){
        if(!RegexUtil.isEmail(em))
            throw new FinalException(ResultInfo.ERROR_PARAM.setMsg(msg));
    }

    public static void phone(String em){
        notNull(em , "手机号码不能为空");
        if(!RegexUtil.isMobile(em))
            throw new FinalException(ResultInfo.ERROR_PARAM.setMsg("手机号格式错误"));
    }

    public static void password(String em){
        notNull(em , "密码不能为空");
        if(!RegexUtil.isPwd(em))
            throw new FinalException(ResultInfo.ERROR_PARAM.setMsg("密码以字母开头，长度在6-16之间，只能包含字符、数字和下划线"));
    }

    public static boolean phoneOrNickname(String em){
        return RegexUtil.isMobile(em);
    }

    public static void nickname(String em){
        notNull(em , "用户名不能为空");
        pattern(em , RegexConfig.NICKNAME_REG , "用户名必须以字母开头，长度在6-16之间，只能包含字符、数字和下划");
    }

    public static void username(String em){
        notNull(em , "姓名不能为空");
        pattern(em , RegexConfig.USERNAME_REG ,"姓名2-10个汉字");
    }




    public static void main(String[] args) throws Exception {
//        pattern("2008-02-29 02:21" , RegexConfig.DATE_TO_SS , "时间格式错误");
//        phone("14088094978" , "电话号码");
//        nickname("w55534");
//        System.out.println(phoneOrNickname("13088094976"));
        phone("13088094976");
    }
}
