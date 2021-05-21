package com.xxx.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.seckill.pojo.SeckillOrder;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xwz
 * @since 2021-05-18
 */
public interface ISeckillOrderService extends IService<SeckillOrder> {

    SeckillOrder findSeckillOrder(Long userId, Long goodsId);
}
