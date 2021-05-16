package com.xxx.seckill.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.xxx.seckill.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @RequestMapping("/toList")
    public String toList(HttpSession session, Model model, @CookieValue("userTicket")String ticket){      //model用于展示商品信息，@CookieValue 用来获取Cookie中的值
        if(StringUtils.isBlank(ticket)){
            return "login";
        }
        //从session中获取user信息
        User user = (User)session.getAttribute(ticket);
        if(null == user){
            return "login";
        }
        model.addAttribute("user",user);
        return "goodsList";
    }
}
