package com.music_recommend_system.front.util;

/**
 * @author ywj
 * @date 2021/11/15
 */

/**
 * @ClassName Result
 * @Description 返回给前端结果
 */
public class Result<T> {
    private Integer status;
    private String message;
    private String token;
    private T data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
