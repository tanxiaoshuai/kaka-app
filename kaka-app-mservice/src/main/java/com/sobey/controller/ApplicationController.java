package com.sobey.controller;

import com.sobey.service.IApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by TS on 2018/3/14.
 */
@RestController
@RequestMapping("rest")
public class ApplicationController {

    @Autowired
    private IApplicationService applicationService;

    @GetMapping("/application/findByUserList")
    public Object findByApplicationList(HttpServletRequest request) throws Exception{
        return applicationService.findByApplicationList(request);
    }

    @GetMapping("/application/findBySiteList")
    public Object findBySiteCodeList(HttpServletRequest request) throws Exception{
        return applicationService.findBySiteCodeList(request);
    }
}
