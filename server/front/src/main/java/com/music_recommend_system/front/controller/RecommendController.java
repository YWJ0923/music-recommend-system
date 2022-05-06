package com.music_recommend_system.front.controller;

import com.music_recommend_system.front.VO.MusicVO;
import com.music_recommend_system.front.dao.MusicMapper;
import com.music_recommend_system.front.dao.UserMapper;
import com.music_recommend_system.front.entity.User;
import com.music_recommend_system.front.service.MusicService;
import com.music_recommend_system.front.service.RecommendService;
import com.music_recommend_system.front.util.Result;
import com.music_recommend_system.front.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ywj
 * @date 2022/04/27
 * @className RecommendController
 * @description
 */
@RestController
public class RecommendController {
    @Autowired
    RecommendService recommendService;


    @GetMapping("/recommend")
    Result listRecommendMusics(@RequestParam("userId") Integer userId) {
        List<MusicVO> recommendMusics = recommendService.listRecommendMusics(userId);
        System.out.println(recommendMusics.size());
        if (recommendMusics != null) {
            return ResultUtils.success(recommendMusics);
        } else {
            return ResultUtils.failure();
        }
    }

}
