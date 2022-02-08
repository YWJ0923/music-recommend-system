package com.ywj.music_recommend_system.front.exception;

/**
 * @Author ywj
 * @Date 2021/12/31
 */
public class AuthorizationException extends RuntimeException{
    public AuthorizationException() {
        super();
    }

    public AuthorizationException(String message) {
        super(message);
    }
}
