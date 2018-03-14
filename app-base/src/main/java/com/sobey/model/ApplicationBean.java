package com.sobey.model;

import com.sobey.dao.annotation.Column;
import com.sobey.dao.annotation.ID;
import com.sobey.dao.annotation.TableName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by TS on 2018/3/14.
 */
@Component
@Scope("prototype")
@TableName(name = "t_application")
public class ApplicationBean {

    @ID(increment = false)
    @Column
    private String applicationid;
    @Column
    private String applicaname;
    @Column
    private String applicationlog;
    @Column
    private String sitecode;
    @Column
    private String applicationurl;
    @Column
    private Integer status;

    public String getApplicationid() {
        return applicationid;
    }

    public void setApplicationid(String applicationid) {
        this.applicationid = applicationid;
    }

    public String getApplicaname() {
        return applicaname;
    }

    public void setApplicaname(String applicaname) {
        this.applicaname = applicaname;
    }

    public String getApplicationlog() {
        return applicationlog;
    }

    public void setApplicationlog(String applicationlog) {
        this.applicationlog = applicationlog;
    }

    public String getSitecode() {
        return sitecode;
    }

    public void setSitecode(String sitecode) {
        this.sitecode = sitecode;
    }

    public String getApplicationurl() {
        return applicationurl;
    }

    public void setApplicationurl(String applicationurl) {
        this.applicationurl = applicationurl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
