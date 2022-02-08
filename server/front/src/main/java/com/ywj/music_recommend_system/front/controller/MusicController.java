package com.ywj.music_recommend_system.front.controller;

import com.ywj.music_recommend_system.front.service.MusicService;
import com.ywj.music_recommend_system.front.util.Result;
import com.ywj.music_recommend_system.front.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @Author ywj
 * @Date 2021/12/01
 */
@RestController
public class MusicController {
    @Autowired
    MusicService musicService;

    @GetMapping("/latestMusics")
    public Result listLatestMusics() {
        return ResultUtils.success(musicService.listLatestMusics());
    }
}
