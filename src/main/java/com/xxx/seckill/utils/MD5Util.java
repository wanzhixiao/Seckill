package com.xxx.seckill.utils;


import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

@Component
public class MD5Util {
    public static final String salt = "1a2b3c4d";

    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }


    /**
     * 输入到表单加密
     * @param inputPass
     * @return
     */
    public static String inputToFormPass(String inputPass){
        String str = " "+salt.charAt(0)+salt.charAt(2)+inputPass+salt.charAt(5)+salt.charAt(4);
        return md5(str);
    }

    /**
     * 表单到数据库二次加密
     * @param formPass
     * @param salt
     * @return
     */
    public static String formPassToDBPass(String formPass,String salt){
        String str = " "+salt.charAt(0)+salt.charAt(2)+formPass+salt.charAt(5)+salt.charAt(4);
        return md5(str);
    }

    /**
     * 最终使用的加密函数
     * @param inputPass
     * @param salt
     * @return
     */
    public static String inputPassToDBPass(String inputPass,String salt){
        String formPass = inputToFormPass(inputPass);
        String dbPass = formPassToDBPass(formPass,salt);
        return dbPass;
    }

    public static void main(String[] args) {
        String inputPass = "123456";
        String formPass = inputToFormPass(inputPass);
        System.out.println(formPass);
        String dbPass = formPassToDBPass(formPass,"1a2b3c4d");
        System.out.println(dbPass);

        String dbPass2 = inputPassToDBPass(inputPass,"1a2b3c4d");
        System.out.println(dbPass2);
    }

}
