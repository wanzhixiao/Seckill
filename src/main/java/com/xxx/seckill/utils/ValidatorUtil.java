package com.xxx.seckill.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtil {
    private static final Pattern mobile_pattern = Pattern.compile("[1]([3-9])[0-9]{9}$");       //第一位是1,第二位是3-9,后面9位是0-9的数字

    //使用正则化校验手机号
    public static boolean isMobile(String mobile){
        if(StringUtils.isBlank(mobile)){
            return false;
        }
        Matcher matcher = mobile_pattern.matcher(mobile);
        return matcher.matches();
    }
}
