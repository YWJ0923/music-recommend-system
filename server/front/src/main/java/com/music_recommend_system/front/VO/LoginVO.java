package com.music_recommend_system.front.VO;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Author ywj
 * @Date 2021/12/23
 */
public class LoginVO {
    @Length(min = 2, message = "用户名太短")
    private String username;
    @Length(min = 2, message = "密码太短")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
