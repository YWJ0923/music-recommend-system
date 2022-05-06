package com.music_recommend_system.front.controller;

import com.music_recommend_system.front.VO.MusicVO;
import com.music_recommend_system.front.entity.User;
import com.music_recommend_system.front.util.JwtUtils;
import com.music_recommend_system.front.util.Result;
import com.music_recommend_system.front.util.ResultUtils;
import com.music_recommend_system.front.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ywj
 * @date 2021/11/15
 */

/**
 * @ClassName UserController
 * @Description 用户Controller
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    /**
     * @Description: 用户注册
     * @Param: [map]
     * @Return: com.music_recommend_system.front.util.Result
     */
    public Result register(@RequestBody Map<String, String> map) {
        String username = map.get("username");
        String password = map.get("password");
        String nickname = map.get("nickname");
        User user = userService.register(username, password, nickname);
        if (user != null) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("userId", user.getUserId());
            hashMap.put("username", user.getUsername());
            return ResultUtils.success("注册成功", JwtUtils.createToken(hashMap), user);
        } else {
            return ResultUtils.failure("用户名已存在");
        }
    }

    @PostMapping("/login")
    /**
     * @Description: 用户登录
     * @Param: [map]
     * @Return: com.music_recommend_system.front.util.Result
     */
    public Result login(@RequestBody Map<String, String> map) {
        String username = map.get("username");
        String password = map.get("password");
        User user = userService.login(username, password);
        if (user == null) {
            return ResultUtils.failure("用户名或密码错误");
        } else {
            Map<String, Object> hashMap = new HashMap<>();
            hashMap.put("userId", user.getUserId());
            hashMap.put("username", user.getUsername());
            String token = JwtUtils.createToken(hashMap);
            return ResultUtils.success("登录成功", token, user);
        }
    }

//    @PostMapping("/login")
//    public Result login(@RequestBody @Valid LoginVO loginVO, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
//            for (FieldError fieldError : fieldErrors) {
//                System.out.println(fieldError.getDefaultMessage());
//            }
//        }
//        return null;
//    }

    @GetMapping("/verify")
    /**
     * @Description: 验证用户登录
     * @Param: [user]
     * @Return: com.music_recommend_system.front.util.Result
     */
    public Result verify(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", user.getUserId());
        map.put("username", user.getUsername());
        return ResultUtils.success("登录成功", JwtUtils.createToken(map), user);
    }

    @GetMapping("/user/music_ranking")
    /**
     * @Description: 查询该用户播放量最高的20首歌
     * @Param: [user]
     * @Return: com.music_recommend_system.front.util.Result
     */
    public Result listUserMusicsOrderByPlayTimes(User user) {
        Integer userId = user.getUserId();
        List<MusicVO> musicVOList = userService.listUserMusicsOrderByPlayTimes(userId);
        return ResultUtils.success("查询成功", musicVOList);
    }
}
