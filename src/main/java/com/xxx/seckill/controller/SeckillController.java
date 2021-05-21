package com.xxx.seckill.controller;

import com.xxx.seckill.pojo.Order;
import com.xxx.seckill.pojo.SeckillOrder;
import com.xxx.seckill.pojo.User;
import com.xxx.seckill.service.IGoodsService;
import com.xxx.seckill.service.IOrderService;
import com.xxx.seckill.service.ISeckillOrderService;
import com.xxx.seckill.vo.GoodsVO;
import com.xxx.seckill.vo.RespBean;
import com.xxx.seckill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/seckill")
public class SeckillController {
    @Autowired
    IGoodsService goodsService;
    @Autowired
    ISeckillOrderService seckillOrderService;
    @Autowired
    IOrderService orderService;

    @RequestMapping("/doSeckill")
    public String doSeckill(Model model, User user, Long goodsId){
        if(null==user){
            return "login";
        }
        model.addAttribute("user",user);
        //查询库存是否足够,为什么是从商品表中查询库存？？
        GoodsVO goods = goodsService.findGoodsVoByGoodsId(goodsId);
        if(goods.getStockCount()<1){
            model.addAttribute("errmsg", RespBeanEnum.REPEATE_ERROR.getMessage());
            return "secKillFail";
        }
        //查询该用户是否秒杀过该商品
        SeckillOrder seckillOrder = seckillOrderService.findSeckillOrder(user.getId(),goodsId);
        if(null != seckillOrder){
            model.addAttribute("errmsg",RespBeanEnum.REPEATE_ERROR.getMessage());
            return "secKillFail";
        }
        //生成订单
        Order order = orderService.seckill(user,goods);
        model.addAttribute("user",user);
        model.addAttribute("goods",goods);
        model.addAttribute("order",order);

        return "orderDetail";
    }
}
