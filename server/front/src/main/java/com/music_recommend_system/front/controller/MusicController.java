package com.music_recommend_system.front.controller;

import com.music_recommend_system.front.service.MusicService;
import com.music_recommend_system.front.util.Result;
import com.music_recommend_system.front.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author ywj
 * @Date 2021/12/01
 */

/**
 * @ClassName MusicController
 * @Description 音乐Controller
 */
@RestController
public class MusicController {
    @Autowired
    MusicService musicService;

    @GetMapping("/latestMusics")
    /**
     * @Description: 查询最新音乐
     * @Param: []
     * @Return: com.music_recommend_system.front.util.Result
     */
    public Result listLatestMusics() {
        return ResultUtils.success(musicService.listLatestMusics());
    }
}
