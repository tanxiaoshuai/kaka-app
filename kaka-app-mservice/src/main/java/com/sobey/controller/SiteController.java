package com.sobey.controller;

import com.sobey.log.RequestLimit;
import com.sobey.service.ISiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/rest")
public class SiteController {

    @Autowired
    private ISiteService siteService;

    @RequestLimit(count = 10 , time = 20)
    @GetMapping("/site/findByIdList/{userid}")
    public Map<String , Object> findByIdList(@PathVariable String userid) throws Exception{
        return siteService.findByIdList(userid);
    }

    @GetMapping("/site/findByList")
    public Map<String , Object> findByList() throws Exception{
        return siteService.findByList();
    }

    @GetMapping("/site/findBySiteNameToList")
    public Map<String , Object> findBySiteNameToList(String sitename) throws Exception{
        return siteService.findBySiteNameToList(sitename);
    }

}
