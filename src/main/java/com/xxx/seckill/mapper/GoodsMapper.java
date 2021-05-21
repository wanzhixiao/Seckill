package com.xxx.seckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxx.seckill.pojo.Goods;
import com.xxx.seckill.vo.GoodsVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xwz
 * @since 2021-05-18
 */
public interface GoodsMapper extends BaseMapper<Goods> {
    List<GoodsVO> findGoodsVo();

    GoodsVO findGoodsVoByGoodsId(Long goodsId);
}
