package com.music_recommend_system.front.exception;

/**
 * @Author ywj
 * @Date 2021/12/31
 */

/**
 * @ClassName AuthorizationException
 * @Description token验证失败异常
 */
public class AuthorizationException extends RuntimeException{
    public AuthorizationException() {
        super();
    }

    public AuthorizationException(String message) {
        super(message);
    }
}
