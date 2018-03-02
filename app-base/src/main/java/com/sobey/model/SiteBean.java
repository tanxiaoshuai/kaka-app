package com.sobey.model;

import com.sobey.dao.annotation.Column;
import com.sobey.dao.annotation.ID;
import com.sobey.dao.annotation.TableName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@TableName(name = "t_site")
public class SiteBean {

    @ID(increment = false)
    @Column
    private String siteid;
    @Column
    private String sitename;
    @Column
    private String sitecode;
    @Column
    private String createtime;
    @Column
    private String details;
    @Column
    private Integer status;

    public String getSiteid() {
        return siteid;
    }

    public void setSiteid(String siteid) {
        this.siteid = siteid;
    }

    public String getSitename() {
        return sitename;
    }

    public void setSitename(String sitename) {
        this.sitename = sitename;
    }

    public String getSitecode() {
        return sitecode;
    }

    public void setSitecode(String sitecode) {
        this.sitecode = sitecode;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"siteid\":\"")
                .append(siteid).append('\"');
        sb.append(",\"sitename\":\"")
                .append(sitename).append('\"');
        sb.append(",\"sitecode\":\"")
                .append(sitecode).append('\"');
        sb.append(",\"createtime\":\"")
                .append(createtime).append('\"');
        sb.append(",\"details\":\"")
                .append(details).append('\"');
        sb.append(",\"status\":")
                .append(status);
        sb.append('}');
        return sb.toString();
    }
}
