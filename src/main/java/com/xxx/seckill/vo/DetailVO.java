package com.xxx.seckill.vo;

import com.xxx.seckill.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailVO {
    private User user;
    private GoodsVO goodsVO;
    private int remainSeconds;
    private int seckillStatus;
}
