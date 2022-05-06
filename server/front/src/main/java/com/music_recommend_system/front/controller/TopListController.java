package com.music_recommend_system.front.controller;

import com.music_recommend_system.front.VO.MusicVO;
import com.music_recommend_system.front.service.TopListService;
import com.music_recommend_system.front.util.Result;
import com.music_recommend_system.front.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author ywj
 * @Date 2022/01/01
 */

/**
 * @ClassName TopListController
 * @Description 排行榜Controller
 */
@RestController
public class TopListController {
    @Autowired
    TopListService topListService;

    @GetMapping("/topList")
    /**
     * @Description: 根据类型查询排行榜
     * @Param: [type]
     * @Return: com.music_recommend_system.front.util.Result
     */
    public Result getTopList(@RequestParam("type") String type) {
        List<MusicVO> topList = topListService.getTopList(type);
        return ResultUtils.success(topList);
    }
}
