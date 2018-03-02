package com.sobey.model;

import com.sobey.dao.annotation.Column;
import com.sobey.dao.annotation.ID;
import com.sobey.dao.annotation.TableName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@TableName(name = "t_job")
public class JobBean {

    @ID(increment = false)
    @Column
    private String jobid;
    @Column
    private String jobname;
    @Column
    private String siteid;
    @Column
    private String details;

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }

    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
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
        sb.append("\"jobid\":\"")
                .append(jobid).append('\"');
        sb.append(",\"jobname\":\"")
                .append(jobname).append('\"');
        sb.append(",\"siteid\":\"")
                .append(siteid).append('\"');
        sb.append(",\"details\":\"")
                .append(details).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
