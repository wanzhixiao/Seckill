package com.xxx.seckill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.seckill.mapper.SeckillOrderMapper;
import com.xxx.seckill.pojo.SeckillOrder;
import com.xxx.seckill.service.ISeckillOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xwz
 * @since 2021-05-18
 */
@Service
public class SeckillOrderServiceImpl extends ServiceImpl<SeckillOrderMapper, SeckillOrder> implements ISeckillOrderService {

    @Autowired
    SeckillOrderMapper seckillOrderMapper;

    public SeckillOrder findSeckillOrder(Long userId, Long goodsId){
//        QueryWrapper<SeckillOrder> queryWrapper = new QueryWrapper();
//        queryWrapper.eq("user_id",userId);
//        queryWrapper.eq("goods_id",goodsId);
         SeckillOrder seckillOrder = seckillOrderMapper.findSeckillOrder(userId,goodsId);
         return seckillOrder;
    }
}
