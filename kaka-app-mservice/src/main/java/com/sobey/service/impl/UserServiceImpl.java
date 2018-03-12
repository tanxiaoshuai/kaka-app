package com.sobey.service.impl;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.sobey.config.AppConfig;
import com.sobey.config.ResultInfo;
import com.sobey.dao.UserDao;
import com.sobey.exception.FinalException;
import com.sobey.model.UserBean;
import com.sobey.redis.RedisUtil;
import com.sobey.util.ResultUtil;
import com.sobey.service.IUserService;
import com.sobey.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

@Service
public class UserServiceImpl implements IUserService{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    @Transactional
    public Map<String, Object> login(UserBean userBean) throws Exception {
        ParamValidateUtil.notNull(userBean.getLoginname() , "用户名不能为空");
        ParamValidateUtil.notNull(userBean.getPwd() , "密码不能为空");
        ParamValidateUtil.notNull(userBean.getDeviceId() , "手机唯一标识不能为空");
        UserBean user = null;
        if (ParamValidateUtil.phoneOrNickname(userBean.getLoginname()))
            user = userDao.userByName(userBean.getLoginname());
        else
            user = userDao.userByPhone(userBean.getLoginname());
        if(user == null)
            throw new FinalException(ResultInfo.USER_ISNULL);
        if(!MD5Util.PWD(userBean.getPwd()).equals(user.getPwd()))
            throw new FinalException(ResultInfo.PASSWORDERROR);
        long s = System.currentTimeMillis();
        String token = TokenUtil.createToken(user.getUserid() , s , userBean.getDeviceId());
        user.setDeviceId(userBean.getDeviceId());
        user.setAppversion(userBean.getAppversion());
        user.setDevicetype(userBean.getDevicetype());
        user.setDevicemodel(userBean.getDevicemodel());
        user.setLoginnumber(user.getLoginnumber() + 1);
        user.setLastlogintime(DateUtil.longForTime(s , DateUtil.YEARTOSS));
        user.setToken(token);
        user.setLoginstatus(1);
        userBean = new UserBean();
        userBean.setUserid(user.getUserid());
        userBean.setAppversion(user.getAppversion());
        userBean.setDeviceId(user.getDeviceId());
        userBean.setDevicetype(user.getDevicetype());
        userBean.setDevicemodel(user.getDevicemodel());
        userBean.setLoginstatus(user.getLoginstatus());
        userBean.setLoginnumber(user.getLoginnumber());
        userBean.setLastlogintime(user.getLastlogintime());
        userDao.updateById(userBean);
        redisUtil.set(KeyUtil.tokenKey(user.getUserid()) , user , AppConfig.REDIS_TOKEN_OUT_TIME);
        return ResultUtil.success(user);
    }

    @Override
    @Transactional
    public Map<String, Object> registe(UserBean userBean , String code) throws Exception {
        ParamValidateUtil.phone(userBean.getPhone());
        ParamValidateUtil.notNull(code , "验证码不能为空");
        String codeKey = KeyUtil.phoneMessageRegisteKey(userBean.getPhone());
        String rcode = (String) redisUtil.get(codeKey);
        if(rcode == null)
            throw new FinalException(ResultInfo.ERROR_PARAM.setMsg("验证码失效"));
        if (!rcode.equals(code))
            throw new FinalException(ResultInfo.ERROR_PARAM.setMsg("验证码错误"));
        UserBean user = userDao.findBySQLRequireToBean("phone = '"+userBean.getPhone()+"'" , UserBean.class);
        if(user != null)
            throw new FinalException(ResultInfo.PHONE_ISNOTNULL);
        ParamValidateUtil.password(userBean.getPwd());
        ParamValidateUtil.nickname(userBean.getNickname());
        user = userDao.findBySQLRequireToBean("nickname = '" + userBean.getNickname() + "'" , UserBean.class);
        if(user != null)
            throw new FinalException(ResultInfo.NICKNAME_ISNOTNULL);
        ParamValidateUtil.username(userBean.getUsername());

        userBean.setUserid(KeyUtil.uuid());
        userBean.setPwd(MD5Util.PWD(userBean.getPwd()));
        userBean.setRegisttime(DateUtil.longForTime(System.currentTimeMillis() , DateUtil.YEARTOSS));
        userBean.setLastlogintime(DateUtil.longForTime(System.currentTimeMillis() , DateUtil.YEARTOSS));
        userBean.setLoginnumber(0L);
        userBean.setLoginstatus(0);
        userBean.setStatus(0);
        userBean.setSitecode(AppConfig.DEFAULT_SITECODE);
        userDao.save(userBean);
        redisUtil.remove(codeKey);
        return ResultUtil.success();
    }

    @Override
    public Map<String, Object> loginOut(HttpServletRequest request) throws Exception {
        String [] param = TokenUtil.tokenToIdAndKey(request.getHeader("token"));
        UserBean userBean = new UserBean();
        userBean.setUserid(param[0]);
        userBean.setLoginstatus(0);
        userDao.updateById(userBean);
        redisUtil.remove(param[1]);
        return ResultUtil.success();
    }

    @Override
    public Map<String, Object> updatePaw(Map<String, String> map , String code) throws Exception {
        String phone = map.get("phone");
        String newpwd = map.get("newpwd");
        ParamValidateUtil.phone(phone);
        ParamValidateUtil.password(newpwd);
        ParamValidateUtil.notNull(code , "验证码不能为空");
        String codeKey = KeyUtil.phoneMessageUpdatePwdKey(phone);
        String rcode = (String) redisUtil.get(codeKey);
        if(rcode == null)
            throw new FinalException(ResultInfo.ERROR_PARAM.setMsg("验证码失效"));
        if (!rcode.equals(code))
            throw new FinalException(ResultInfo.ERROR_PARAM.setMsg("验证码错误"));
        userDao.updateBySQLRequire("pwd = '" + MD5Util.PWD(newpwd) +"' where phone = '" + phone + "'" , UserBean.class );
        redisUtil.remove(codeKey);
        return ResultUtil.success();
    }

    @Override
    public Map<String, Object> sendSmsMessage(String phone , String type) throws Exception {
        ParamValidateUtil.phone(phone);
        ParamValidateUtil.notNull(type , "短信验证码不能为空");
        StringBuffer code = new StringBuffer();
        for(int i = 0 ; i < AppConfig.AL_SMS_MESSAGE_CODE_SIZE ; i++){
            code.append((int) (Math.random() * 10));
        }
        //type 1 注册 2修改
        String templateCode = null;
        String smsKey = null;
        int count = 0;
        Set<Integer> keyCode = AppConfig.AL_SMS_TEMPLATECODE.keySet();
        for(Integer mtype : keyCode){
            if(mtype.equals(type)){
                templateCode = AppConfig.AL_SMS_TEMPLATECODE.get(mtype);
                smsKey = KeyUtil.phoneMessageRegisteKey(phone);
                break;
            }
            count++;
        }
        if(count == keyCode.size())
            throw new FinalException(ResultInfo.ERROR_PARAM.setMsg("短信验证码类型错误"));
        SendSmsResponse sendSmsResponse = SmsSendMessage.sendSms(phone ,
                "{\"code\": \""+code.toString()+"\"}", templateCode);
        LOGGER.info("获取阿里验证码返回参数： " + JSONObject.toJSONString(sendSmsResponse));
        String respCode = sendSmsResponse.getCode().toLowerCase();
        if(!respCode.equals("ok"))
            throw new FinalException(ResultInfo.AL_SMS_MESSAGE_ERROR);
        redisUtil.set(smsKey , code , AppConfig.AL_SMS_OUTTIME);
        return ResultUtil.success();
    }
}
