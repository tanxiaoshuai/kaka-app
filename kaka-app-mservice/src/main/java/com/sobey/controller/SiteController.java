package com.sobey.controller;

import com.sobey.service.ISiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/rest")
public class SiteController {

    @Autowired
    private ISiteService siteService;

    @GetMapping("/site/findByIdList/{userid}")
    public Map<String , Object> findByIdList(@PathVariable String userid) throws Exception{
        return siteService.findByIdList(userid);
    }


}
