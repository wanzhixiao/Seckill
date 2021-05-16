package com.xxx.seckill.vo;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.xxx.seckill.utils.ValidatorUtil;
import com.xxx.seckill.validator.IsMobile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


//校验类需要实现ConstraintValidator接口，第一个自定义注解类，第二个为需要校验的数据类型
//需要重写ConstraintValidator接口中的两个方法
public class IsMobileValidator implements ConstraintValidator<IsMobile,String> {

    private boolean required = false;

    @Override
    public void initialize(IsMobile constraintAnnotation) {     //做一些初始化操作
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext context) {   //终需要的校验方法
        if(required){                                    //如果是必须字段
            return ValidatorUtil.isMobile(s);
        }else{
            if(StringUtils.isBlank(s)){
                return false;
            }else{
                return ValidatorUtil.isMobile(s);       //执行真正的校验方法
            }
        }
    }
}
