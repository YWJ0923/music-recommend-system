package com.music_recommend_system.front.controller;

import com.music_recommend_system.front.entity.User;
import com.music_recommend_system.front.util.Result;
import com.music_recommend_system.front.util.ResultUtils;
import com.music_recommend_system.front.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @Author ywj
 * @Date 2022/02/12
 */

/**
 * @ClassName ScoreController
 * @Description 音乐评分Controller
 */
@Controller
public class ScoreController {
    @Autowired
    ScoreService scoreService;

    @PostMapping("/score")
    public Result insertScore(User user, @RequestBody Map<String, Object> map) {
        String musicId = (String) map.get("musicId");
        Integer length = (Integer) map.get("length");
        Integer timeLength = (Integer) map.get("timeLength");
        if (length == null) {
            if (scoreService.insertScore(user.getUserId(), musicId, 10.0)) {
                return ResultUtils.success();
            }
        } else {
            if (scoreService.insertScore(user.getUserId(), musicId, length * 1.0 / timeLength * 8)) {
                return ResultUtils.success();
            }
        }
        return ResultUtils.failure();
    }
}
