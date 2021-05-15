package com.xxx.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.seckill.pojo.User;
import com.xxx.seckill.vo.LoginVo;
import com.xxx.seckill.vo.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xwz
 * @since 2021-05-15
 */
public interface IUserService extends IService<User> {

    RespBean login(LoginVo loginVo);
}
