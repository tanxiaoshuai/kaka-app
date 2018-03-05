package com.sobey.model;
import com.sobey.dao.annotation.Column;
import com.sobey.dao.annotation.ID;
import com.sobey.dao.annotation.TableName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by TS on 2018/2/23.
 */
@Component
@Scope("prototype")
@TableName(name = "t_user")
public class UserBean {

    /**
     * 用户id
     */
    @ID(increment = false)
    @Column
    private String  userid;

    /**
     * 头像
     */
    @Column
    private String headimg;

    /**
     * 用户名
     */
    @Column
    private String username;

    /**
     * 手机号码
     */
    @Column
    private String phone;

    /**
     * 密码
     */
    @Column
    private String pwd;

    /**
     * 注册时间
     */
    @Column
    private String registtime;

    /**
     * 上次登录时间
     */
    @Column
    private String lastlogintime;

    /**
     * 手机类型（Android ios）
     */
    @Column
    private String devicetype;

    /**
     * app版本
     */
    @Column
    private String appversion;

    /**
     * app型号
     */
    @Column
    private String devicemodel;

    /**
     * 手机唯一标识
     */
    @Column
    private String deviceId;

    /**
     * token
     */
    private String token;

    /**
     * 用户状态（0 白名单 1黑名单）
     */
    @Column
    private Integer status;

    /**
     * 头像
     */
    @Column
    private  String emil;

    /**
     * 登录次数
     */
    @Column
    private Long loginnumber;

    /**
     * 在线状态（1 在线 0 离线）
     */
    @Column
    private Integer loginstatus;

    /**
     * 个人磁盘空间
     */
    @Column
    private Long disksize;

    /**
     * 站点code
     */
    @Column
    private String sitecode;

    /**
     * 站点名称
     */
    private String sitename;

    /**
     * 部门名称
     */
    private String departmentname;

    /**
     * 职位名称
     */
    private String jobname;

    private List<SiteBean> sitelist;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRegisttime() {
        return registtime;
    }

    public void setRegisttime(String registtime) {
        this.registtime = registtime;
    }

    public String getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(String lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    public String getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype;
    }

    public String getAppversion() {
        return appversion;
    }

    public void setAppversion(String appversion) {
        this.appversion = appversion;
    }

    public String getDevicemodel() {
        return devicemodel;
    }

    public void setDevicemodel(String devicemodel) {
        this.devicemodel = devicemodel;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getEmil() {
        return emil;
    }

    public void setEmil(String emil) {
        this.emil = emil;
    }

    public Long getLoginnumber() {
        return loginnumber;
    }

    public void setLoginnumber(Long loginnumber) {
        this.loginnumber = loginnumber;
    }

    public Integer getLoginstatus() {
        return loginstatus;
    }

    public void setLoginstatus(Integer loginstatus) {
        this.loginstatus = loginstatus;
    }

    public Long getDisksize() {
        return disksize;
    }

    public void setDisksize(Long disksize) {
        this.disksize = disksize;
    }

    public String getSitecode() {
        return sitecode;
    }

    public void setSitecode(String sitecode) {
        this.sitecode = sitecode;
    }

    public String getSitename() {
        return sitename;
    }

    public void setSitename(String sitename) {
        this.sitename = sitename;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
    }

    public List<SiteBean> getSitelist() {
        return sitelist;
    }

    public void setSitelist(List<SiteBean> sitelist) {
        this.sitelist = sitelist;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"userid\":\"")
                .append(userid).append('\"');
        sb.append(",\"headimg\":\"")
                .append(headimg).append('\"');
        sb.append(",\"username\":\"")
                .append(username).append('\"');
        sb.append(",\"phone\":\"")
                .append(phone).append('\"');
        sb.append(",\"pwd\":\"")
                .append(pwd).append('\"');
        sb.append(",\"registtime\":\"")
                .append(registtime).append('\"');
        sb.append(",\"lastlogintime\":\"")
                .append(lastlogintime).append('\"');
        sb.append(",\"devicetype\":\"")
                .append(devicetype).append('\"');
        sb.append(",\"appversion\":\"")
                .append(appversion).append('\"');
        sb.append(",\"devicemodel\":\"")
                .append(devicemodel).append('\"');
        sb.append(",\"deviceId\":\"")
                .append(deviceId).append('\"');
        sb.append(",\"token\":\"")
                .append(token).append('\"');
        sb.append(",\"status\":")
                .append(status);
        sb.append(",\"emil\":\"")
                .append(emil).append('\"');
        sb.append(",\"loginnumber\":")
                .append(loginnumber);
        sb.append(",\"loginstatus\":")
                .append(loginstatus);
        sb.append(",\"disksize\":")
                .append(disksize);
        sb.append(",\"sitecode\":\"")
                .append(sitecode).append('\"');
        sb.append(",\"sitename\":\"")
                .append(sitename).append('\"');
        sb.append(",\"departmentname\":\"")
                .append(departmentname).append('\"');
        sb.append(",\"jobname\":\"")
                .append(jobname).append('\"');
        sb.append(",\"sitelist\":")
                .append(sitelist);
        sb.append('}');
        return sb.toString();
    }
}
