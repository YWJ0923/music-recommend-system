package com.ywj.music_recommend_system.back.util;

/**
 * @Author ywj
 * @Date 2021/12/01
 */
public class ResultUtils {
    public static final Integer SUCCESS = 1;
    public static final Integer FAILURE = 0;
    public static final Integer INVALID_TOKEN = 2;
    public static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";
    public static final String DEFAULT_FAILURE_MESSAGE = "FAILURE";
    public static final String DEFAULT_INVALID_TOKEN_MESSAGE = "INVALID_TOKEN";


    public static Result success(String message) {
        Result<Object> result = new Result();
        result.setStatus(SUCCESS);
        result.setMessage(message);
        return result;
    }

    public static <T> Result success(T data) {
        Result<T> result = new Result<>();
        result.setStatus(SUCCESS);
        result.setMessage(DEFAULT_SUCCESS_MESSAGE);
        result.setData(data);
        return result;
    }

    public static Result success(String message, String token) {
        Result<Object> result = new Result();
        result.setStatus(SUCCESS);
        result.setMessage(message);
        result.setToken(token);
        return result;
    }

    public static <T> Result success(String message, T data) {
        Result<Object> result = new Result();
        result.setStatus(SUCCESS);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static <T> Result success(String message, String token, T data) {
        Result<T> result = new Result<>();
        result.setStatus(SUCCESS);
        result.setMessage(message);
        result.setToken(token);
        result.setData(data);
        return result;
    }

    public static Result failure() {
        Result<Object> result = new Result<>();
        result.setStatus(FAILURE);
        result.setMessage(DEFAULT_FAILURE_MESSAGE);
        return result;
    }

    public static Result failure(String message) {
        Result<Object> result = new Result();
        result.setStatus(FAILURE);
        result.setMessage(message);
        return result;
    }

    public static Result failure(String message, String token) {
        Result<Object> result = new Result();
        result.setStatus(FAILURE);
        result.setMessage(message);
        result.setToken(token);
        System.out.println(result);
        return result;
    }

    public static <T> Result failure(String message, T data) {
        Result<Object> result = new Result();
        result.setStatus(FAILURE);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static <T> Result failure(String message, String token, T data) {
        Result<T> result = new Result<>();
        result.setStatus(FAILURE);
        result.setMessage(message);
        result.setToken(token);
        result.setData(data);
        return result;
    }

    public static Result invalidToken() {
        Result<Object> result = new Result<>();
        result.setStatus(INVALID_TOKEN);
        return result;
    }
}
