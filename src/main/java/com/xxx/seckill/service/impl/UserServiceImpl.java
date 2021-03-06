package com.xxx.seckill.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.seckill.exception.GlobalException;
import com.xxx.seckill.mapper.UserMapper;
import com.xxx.seckill.pojo.User;
import com.xxx.seckill.service.IUserService;
import com.xxx.seckill.utils.CookieUtil;
import com.xxx.seckill.utils.MD5Util;
import com.xxx.seckill.utils.UUIDUtil;
import com.xxx.seckill.utils.ValidatorUtil;
import com.xxx.seckill.vo.LoginVo;
import com.xxx.seckill.vo.RespBean;
import com.xxx.seckill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xwz
 * @since 2021-05-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public RespBean login(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        //判断用户名或密码是否为空
//        if(StringUtils.isBlank(mobile) || StringUtils.isBlank(password)){
//            return RespBean.error(RespBeanEnum.LOGINVO_ERROR);
//        }
//        //校验手机号格式是否正确
//        if(!ValidatorUtil.isMobile(mobile)){
//            return RespBean.error(RespBeanEnum.MOBILE_ERROR);
//        }
        //根据手机号获取用户
        User user = userMapper.selectById(mobile);
        if(null==user){
//            return RespBean.error(RespBeanEnum.LOGINVO_ERROR);
            throw new GlobalException(RespBeanEnum.LOGINVO_ERROR);      //此处抛出的异常会自动被全局注解 @ExceptionHandler捕获
        }
        //校验密码,前端已经加密过一次，所以只需再加密一次
        if(!MD5Util.formPassToDBPass(password,user.getSalt()).equals(user.getPassword())){
//            return RespBean.error(RespBeanEnum.LOGINVO_ERROR);
            throw new GlobalException(RespBeanEnum.LOGINVO_ERROR);
        }

        //将用户信息存入session↓↓
        //生成用户的uuid,作为识别用户的编号
        String ticket = UUIDUtil.uuid();
        //获取session
//        request.getSession().setAttribute(ticket,user);

        //将用户信息存入session中
        redisTemplate.opsForValue().set("user"+ticket,user);

        //将cookie信息放入session
        CookieUtil.setCookie(request, response, "userTicket", ticket);
        return RespBean.success(ticket);
    }

    /***
     * 根据User获取用户
     * @param userTicket
     * @param request
     * @param response
     * @return
     */

    public User getUserByCookie(String userTicket,HttpServletRequest request,HttpServletResponse response){
        if(StringUtils.isBlank(userTicket)){
            return null;
        }
        //从redis中获取用户信息
        User user = (User)redisTemplate.opsForValue().get("user"+userTicket);
        if(null != user){
            CookieUtil.setCookie(request,response,"userTicket",userTicket);
        }
        return user;
    }
}
