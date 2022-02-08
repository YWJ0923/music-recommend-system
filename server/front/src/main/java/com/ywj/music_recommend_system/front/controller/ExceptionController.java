package com.ywj.music_recommend_system.front.controller;

import com.ywj.music_recommend_system.front.exception.AuthorizationException;
import com.ywj.music_recommend_system.front.util.Result;
import com.ywj.music_recommend_system.front.util.ResultUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author ywj
 * @Date 2021/12/31
 */
@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(AuthorizationException.class)
    public Result handleAuthorizationException() {
        return ResultUtils.invalidToken();
    }
}
