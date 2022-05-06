package com.music_recommend_system.front.controller;

import com.music_recommend_system.front.entity.User;
import com.music_recommend_system.front.service.MusicService;
import com.music_recommend_system.front.service.ScoreService;
import com.music_recommend_system.front.util.Result;
import com.music_recommend_system.front.util.ResultUtils;
import com.music_recommend_system.front.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author ywj
 * @Date 2021/12/24
 */

/**
 * @ClassName PlayTimesController
 * @Description 播放量Controller
 */
@RestController
public class PlayTimesController {
    @Autowired
    UserService userService;
    @Autowired
    MusicService musicService;
    @Autowired
    ScoreService scoreService;

    // 增加播放量同时，修改评分
    @PostMapping("/user/play_times/{musicId}")
    /**
     * @Description: 增加用户播放记录
     * @Param: [user, musicId, map]
     * @Return: com.music_recommend_system.front.util.Result
     */
    public Result insertUserPlayTimes(User user, @PathVariable String musicId, @RequestBody Map<String, Object> map) {
        scoreService.insertScore(user.getUserId(), musicId, 5.0);
        Integer timeLength = (Integer) map.get("time_length");
        if (userService.insertUserPlayTimes(user.getUserId(), musicId, timeLength)) {
            return ResultUtils.success("用户播放量修改成功");
        } else {
            return ResultUtils.failure("用户播放量修改失败");
        }
    }

    @PostMapping("/play_times/{musicId}")
    /**
     * @Description: 修改音乐播放量
     * @Param: [musicId]
     * @Return: com.music_recommend_system.front.util.Result
     */
    public Result updatePlayTimesByMusicId(@PathVariable("musicId") String musicId) {
        if (musicService.updatePlayTimesByMusicId(musicId)) {
            return ResultUtils.success("播放量修改成功");
        } else {
            return ResultUtils.failure("播放量修改失败");
        }
    }
}
