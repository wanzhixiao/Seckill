package com.xxx.seckill.controller;

import com.xxx.seckill.service.IUserService;
import com.xxx.seckill.vo.LoginVo;
import com.xxx.seckill.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;


@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private IUserService userService;



    /**
     * 跳转到登录页面
     */
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    /**
     * 登录方法
     * @param loginVo
     * @return
     */
    @RequestMapping("/doLogin")
    @ResponseBody
    public RespBean doLogin(@Valid LoginVo loginVo){
        System.out.println(loginVo.toString());

        return userService.login(loginVo);
    }
}
