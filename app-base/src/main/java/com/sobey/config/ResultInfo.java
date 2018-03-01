package com.sobey.config;

/**
 * Created by TS on 2018/2/24.
 */
public enum  ResultInfo {

    SUCCESS("0000","操作成功"),
    ERROR_PARAM("0001","参数错误"),
    EXCEPTION("0002","系统异常"),
    NOAUTHORIZE("0003" , "未授权失败"),
    LOGINOUTTIME("0004" , "登录失效"),
    ANOTHERdDEVICELOGIN("0005" , "用户在其他设备上登录"),
    USERISNULL("0006" , "用户不存在"),
    USERISNOTNULL("0007" , "用户存在"),
    PASSWORDERROR("0008" , "密码错误"),




    ;

    private String code;

    private String msg;

    ResultInfo(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public ResultInfo setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResultInfo setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
