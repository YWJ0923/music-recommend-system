package com.ywj.music_recommend_system.front.controller;

import com.ywj.music_recommend_system.front.VO.MusicVO;
import com.ywj.music_recommend_system.front.service.TopListService;
import com.ywj.music_recommend_system.front.util.Result;
import com.ywj.music_recommend_system.front.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author ywj
 * @Date 2022/01/01
 */
@RestController
public class TopListController {
    @Autowired
    TopListService topListService;

    @GetMapping("/topList")
    public Result getTopList(@RequestParam("type") String type) {
        List<MusicVO> topList = topListService.getTopList(type);
        return ResultUtils.success(topList);
    }
}
