package com.music_recommend_system.front.controller;

import com.music_recommend_system.front.entity.User;
import com.music_recommend_system.front.util.Result;
import com.music_recommend_system.front.util.ResultUtils;
import com.music_recommend_system.front.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author ywj
 * @Date 2022/02/10
 */

/**
 * @ClassName TagController
 * @Description 标签Controller
 */
@RestController
public class TagController {
    @Autowired
    TagService tagService;

    @GetMapping("/tag")
    /**
     * @Description: 查询所有标签
     * @Param: []
     * @Return: com.music_recommend_system.front.util.Result
     */
    public Result listTags() {
        return ResultUtils.success(tagService.listTags());
    }

    @PostMapping("/user_tag")
    /**
     * @Description: 增加用户喜好的标签
     * @Param: [user, map]
     * @Return: com.music_recommend_system.front.util.Result
     */
    public Result userTag(User user, @RequestBody Map<String, Object> map) {
        List<Integer> tags = (List<Integer>) map.get("tags");
        if (tags.size() > 0) {
            if (tagService.insertUserTags(user.getUserId(), tags)) {
                return ResultUtils.success();
            }
        }
        return ResultUtils.failure();
    }
}
