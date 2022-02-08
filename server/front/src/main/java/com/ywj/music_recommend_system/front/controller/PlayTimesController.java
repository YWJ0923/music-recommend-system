package com.ywj.music_recommend_system.front.controller;

import com.ywj.music_recommend_system.front.entity.User;
import com.ywj.music_recommend_system.front.service.MusicService;
import com.ywj.music_recommend_system.front.service.UserService;
import com.ywj.music_recommend_system.front.util.JwtUtils;
import com.ywj.music_recommend_system.front.util.Result;
import com.ywj.music_recommend_system.front.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author ywj
 * @Date 2021/12/24
 */
@RestController
public class PlayTimesController {
    @Autowired
    UserService userService;
    @Autowired
    MusicService musicService;

    @PostMapping("/user/play_times/{musicId}")
    public Result insertUserPlayTimes(User user, @PathVariable String musicId, @RequestBody Map<String, Object> map) {
        Integer timeLength = (Integer) map.get("time_length");
        if (userService.insertUserPlayTimes(user.getUserId(), musicId, timeLength)) {
            return ResultUtils.success("用户播放量修改成功");
        } else {
            return ResultUtils.failure("用户播放量修改失败");
        }
    }

    @PostMapping("/play_times/{musicId}")
    public Result updatePlayTimesByMusicId(@PathVariable("musicId") String musicId) {
        if (musicService.updatePlayTimesByMusicId(musicId)) {
            return ResultUtils.success("播放量修改成功");
        } else {
            return ResultUtils.failure("播放量修改失败");
        }
    }
}
