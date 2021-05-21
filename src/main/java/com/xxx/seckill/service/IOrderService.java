package com.xxx.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.seckill.pojo.Order;
import com.xxx.seckill.pojo.User;
import com.xxx.seckill.vo.GoodsVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xwz
 * @since 2021-05-18
 */
public interface IOrderService extends IService<Order> {

    Order seckill(User user, GoodsVO goods);
}
