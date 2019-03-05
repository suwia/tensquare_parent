package com.tensquare.base.controller;

import com.tensquare.common.domain.Result;
import com.tensquare.common.domain.StatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: sirc_hzr
 * @Date: 2019/3/5 10:20
 * @ClassName: BaseExceptionHandler
 * @Version: 1.0
 * @Description:
 */

@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result error(Exception e) {
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR, e.getMessage());
    }
}
