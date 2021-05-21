package com.xxx.seckill.controller;

import com.xxx.seckill.pojo.Goods;
import com.xxx.seckill.pojo.User;
import com.xxx.seckill.service.IGoodsService;
import com.xxx.seckill.vo.DetailVO;
import com.xxx.seckill.vo.GoodsVO;
import com.xxx.seckill.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/goods")
public class GoodsController {
     @Autowired
     private IGoodsService goodsService;

//    @RequestMapping("/toList")
//    public String toList(Model model, @CookieValue("userTicket")String ticket){      //model用于展示商品信息，@CookieValue 用来获取Cookie中的值
//        if(StringUtils.isBlank(ticket)){
//            return "login";
//        }
//        //从session中获取user信息
//        User user = (User)session.getAttribute(ticket);
//        if(null == user){
//            return "login";
//        }
//        model.addAttribute("user",user);
//        return "goodsList";
//    }


        @RequestMapping("/toList")
        public String toList(Model model,User user){      //使用WebConfig @Configuration,@EnableWebMvc自定义参数校验
            model.addAttribute("user",user);
            model.addAttribute("goodsList",goodsService.findGoodsVO());
            return "goodsList";
        }

        @RequestMapping("/toDetail/{goodsId}")        //请求后面带一个参数
        public String toDetail(User user, Model model, @PathVariable Long goodsId){

            System.out.println("goodsId="+goodsId);

            GoodsVO goods = goodsService.findGoodsVoByGoodsId(goodsId);
            Date startDate = goods.getStartDate();
            Date endDate = goods.getEndDate();
            Date nowDate = new Date();

            //秒杀状态
            int secKillStatus = 0;
            //秒杀倒计时
            int remainSeconds = 0;

            if(nowDate.before(startDate)){      //秒杀未开始
                remainSeconds = (int)((startDate.getTime()-nowDate.getTime())/1000);
            }else if(nowDate.after(endDate)){   //秒杀已结束
                secKillStatus = 2;
                remainSeconds = -1;
            }else{                              //秒杀进行中
                secKillStatus = 1;
                remainSeconds = 0;
            }

            model.addAttribute("goods",goods);
            model.addAttribute("user",user);
            model.addAttribute("remainSeconds",remainSeconds);
            model.addAttribute("secKillStatus",secKillStatus);

            return "goodsDetail";
        }


}
