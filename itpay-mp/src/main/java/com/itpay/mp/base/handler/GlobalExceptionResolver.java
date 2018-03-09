package com.itpay.mp.base.handler;

import com.itpay.restfull.ResultCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author lfeng1
 * @date 2018/3/9 0009
 * spring mvc 全局异常
 */
@ControllerAdvice
public class GlobalExceptionResolver {

    /**
     *restful  接口异常处理
     * @param e 异常类型
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultCode restHandleException( Exception e){
        return new ResultCode(ResultCode.ERROR,e.getMessage());
    }


}
