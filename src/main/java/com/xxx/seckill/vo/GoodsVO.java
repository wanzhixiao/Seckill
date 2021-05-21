package com.xxx.seckill.vo;

import com.xxx.seckill.pojo.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsVO extends Goods {
    private BigDecimal seckillPrice;
    private int stockCount;
    private Date startDate;
    private Date endDate;
}
