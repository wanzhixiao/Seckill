package com.xxx.seckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxx.seckill.pojo.SeckillOrder;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xwz
 * @since 2021-05-18
 */
public interface SeckillOrderMapper extends BaseMapper<SeckillOrder> {

    SeckillOrder findSeckillOrder(Long userId, Long goodsId);
}
