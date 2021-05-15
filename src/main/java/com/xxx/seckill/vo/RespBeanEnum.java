package com.xxx.seckill.vo;

public enum RespBeanEnum implements CodeEnum {
    //通用状态码
    SUCCESS(200,"success"),
    ERROR(500,"服务端异常"),
    //登录模块5002xx
    SESSION_ERROR(500210,"session不存在或者已经失效"),
    LOGINVO_ERROR(500211,"用户名或者密码错误"),
    MOBILE_ERROR(500212,"手机号码格式错误");

    public Integer code;
    public String message;

    //构造函数
    RespBeanEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }
    RespBeanEnum(){}

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "RespBeanEnum{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}


