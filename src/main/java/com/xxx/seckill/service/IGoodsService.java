package com.xxx.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.seckill.pojo.Goods;
import com.xxx.seckill.vo.GoodsVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xwz
 * @since 2021-05-18
 */
public interface IGoodsService extends IService<Goods> {

    List<GoodsVO> findGoodsVO();

    GoodsVO findGoodsVoByGoodsId(Long goodsId);
}
