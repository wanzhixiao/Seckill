package com.xxx.seckill.exception;

import com.xxx.seckill.vo.RespBean;
import com.xxx.seckill.vo.RespBeanEnum;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理类
 */
@RestControllerAdvice //返回的是ResponseBody,他使得其实现类能够被classpath扫描自动发现
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public RespBean ExceotionHandler(Exception e){
        if(e instanceof GlobalException){               //全局异常
            GlobalException ex = (GlobalException)e;
            return RespBean.error(ex.getRespBeanEnum());
        }else if(e instanceof BindException){           //参数绑定异常
            BindException ex = (BindException)e;
            RespBean respBean = RespBean.error(RespBeanEnum.BIND_ERROR);
            respBean.setMessage(respBean.getMessage()+":"+ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
            return respBean;
        }
        return RespBean.error(RespBeanEnum.ERROR);
    }
}
