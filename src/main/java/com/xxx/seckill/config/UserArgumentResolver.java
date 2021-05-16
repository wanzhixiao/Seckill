package com.xxx.seckill.config;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.xxx.seckill.pojo.User;
import com.xxx.seckill.service.IUserService;
import com.xxx.seckill.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    IUserService userService;

    /**
     * 校验方法，此方法返回true才会执行resolveArgument
     * @param parameter
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> classz = parameter.getParameterType();
        return classz== User.class;
    }

    /**
     * 更具cookie获取用户
     * @param methodParameter
     * @param modelAndViewContainer
     * @param nativeWebRequest
     * @param webDataBinderFactory
     * @return
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
        String userTicket = CookieUtil.getCookieValue(request,"userTicket");
        if(StringUtils.isBlank(userTicket)){
            return null;
        }
        return userService.getUserByCookie(userTicket,request,response);
    }
}
