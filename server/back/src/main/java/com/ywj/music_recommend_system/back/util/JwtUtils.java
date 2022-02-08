package com.ywj.music_recommend_system.back.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ywj.music_recommend_system.entity.User;
import com.ywj.music_recommend_system.service.UserService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * @Author ywj
 * @Date 2021/12/01
 */
@Component
public class JwtUtils {
    private static UserService userService;
    private static String key = "1998yangwenjie0923";

    @Autowired
    public void setUserService(UserService userService) {
        JwtUtils.userService = userService;
    }

    /**
     * @Description: 根据传入数据，创建token
     * @Param: 包含data的map
     * @Return: token字符串
     */
    public static String createToken(Map<String, Object> map) {
        Algorithm algorithm = Algorithm.HMAC256(key);
        return JWT.create()
                .withIssuer("718音乐")
                .withExpiresAt(DateUtils.addWeeks(new Date(), 1))
                .withPayload(map)
                .sign(algorithm);
    }

    /**
     * @Description: 传入token，判断它是否有效
     * @Param:
     * @Return: User，包含密码
     */
    public static User verifyToken(String token) {
        Map<String, Claim> claims = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(key);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer("718音乐").build();
            DecodedJWT jwt = verifier.verify(token);
            claims = jwt.getClaims();
        } catch (IllegalArgumentException e) {
            return null;
        } catch (JWTVerificationException e) {
            return null;
        }
        Integer userId = claims.get("userId").asInt();
        String username = claims.get("username").asString();
        User user = userService.verify(userId, username);
        return user;
    }
}
