package com.music_recommend_system.front.config;

import com.music_recommend_system.front.entity.User;
import com.music_recommend_system.front.exception.AuthorizationException;
import com.music_recommend_system.front.util.JwtUtils;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author ywj
 * @Date 2021/12/31
 */

/**
 * @ClassName UserArgumentResolver
 * @Description 从请求头中获取User信息
 */
@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> clazz = parameter.getParameterType();
        return clazz == User.class;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        String token = request.getHeader("Authorization");
        if (!StringUtils.hasLength(token)) {
            throw new AuthorizationException();
        }
        User user = JwtUtils.verifyToken(token);
        if (user == null) {
            throw new AuthorizationException();
        }
        return user;
    }
}
