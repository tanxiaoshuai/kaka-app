package com.sobey.controller;
import com.sobey.model.UserBean;
import com.sobey.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}
