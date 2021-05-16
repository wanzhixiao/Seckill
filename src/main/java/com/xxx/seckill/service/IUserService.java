package com.xxx.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.seckill.pojo.User;
import com.xxx.seckill.vo.LoginVo;
import com.xxx.seckill.vo.RespBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xwz
 * @since 2021-05-15
 */
public interface IUserService extends IService<User> {

    RespBean login(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response);
    User getUserByCookie(String userTicket,HttpServletRequest request,HttpServletResponse response);
}
