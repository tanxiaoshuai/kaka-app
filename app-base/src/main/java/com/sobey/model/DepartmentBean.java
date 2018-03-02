package com.sobey.model;

import com.sobey.dao.annotation.Column;
import com.sobey.dao.annotation.ID;
import com.sobey.dao.annotation.TableName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@TableName(name = "t_department")
public class DepartmentBean {
    @ID(increment = false)
    @Column
    private String departmentid;
    @Column
    private String departmentname;
    @Column
    private String siteid;
    @Column
    private String details;

    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public String getSiteid() {
        return siteid;
    }

    public void setSiteid(String siteid) {
        this.siteid = siteid;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"departmentid\":\"")
                .append(departmentid).append('\"');
        sb.append(",\"departmentname\":\"")
                .append(departmentname).append('\"');
        sb.append(",\"siteid\":\"")
                .append(siteid).append('\"');
        sb.append(",\"details\":\"")
                .append(details).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
