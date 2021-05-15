package com.xxx.seckill.vo;

import com.xxx.seckill.validator.IsMobile;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data  //get()、 set()、 toString()
public class LoginVo {
    @NotNull                    //非空注解
    @IsMobile                   //自定义注解
    private String mobile;

    @NotNull
    @Length(min=32)
    private String password;
}

