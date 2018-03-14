package com.sobey.controller;
import com.sobey.model.UserBean;
import com.sobey.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by TS on 2018/2/23.
 */
@RestController
@RequestMapping("/rest")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/user/login")
    public Object login(@RequestBody UserBean userBean) throws Exception{
        return userService.login(userBean);
    }

    @PostMapping("/user/registe")
    public Object registe(@RequestBody UserBean userBean , String code) throws Exception{
        return userService.registe(userBean , code);
    }

    @GetMapping("/user/loginOut")
    public Object loginOut(HttpServletRequest request)throws Exception{
        return userService.loginOut(request);
    }

    @PostMapping("/user/updatepwd")
    public Object updatepwd (@RequestBody Map<String , String> map , String code) throws Exception{
        return userService.updatePaw(map , code);
    }

    @GetMapping("/sms/getMessageCode")
    public Object sendSmsMessage(String phone , Integer type) throws Exception{
        return userService.sendSmsMessage(phone , type);
    }

    @PostMapping("/user/pwdequals")
    public Object updatePhonePwdEquals(String pwd , HttpServletRequest request) throws Exception{
        return userService.updatePhonePwdEquals(pwd , request);
    }

    @PostMapping("/user/update/phone")
    public Object updatePhone(String newphone , String code , HttpServletRequest request) throws Exception{
        return userService.updatePhone(newphone , code , request);
    }


}
