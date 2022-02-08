package com.ywj.music_recommend_system.front.controller;

import com.ywj.music_recommend_system.front.VO.CollectionListVO;
import com.ywj.music_recommend_system.front.entity.User;
import com.ywj.music_recommend_system.front.service.CollectionService;
import com.ywj.music_recommend_system.front.util.JwtUtils;
import com.ywj.music_recommend_system.front.util.Result;
import com.ywj.music_recommend_system.front.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author ywj
 * @Date 2021/12/02
 */
@RestController
public class CollectionController {
    @Autowired
    CollectionService collectionService;

    @GetMapping("/collection_list")
    public Result listCollectionListsByUserId(User user) {
        List<CollectionListVO> collectionListVOList = collectionService.listCollectionlistsByUserId(user.getUserId());
        return ResultUtils.success("成功", collectionListVOList);
    }

    @GetMapping("/collection_list/{collectionListId}")
    public Result getCollectionListByCollectionListId(User user, @PathVariable("collectionListId") Integer collectionListId) {
        CollectionListVO collectionListVO = collectionService.getCollectionListByCollectionListId(collectionListId);
        return ResultUtils.success("成功", collectionListVO);
    }

    @PostMapping("/collection_list/{collectionListId}/{musicId}")
    public Result insertMusicToCollectionList(User user, @PathVariable("collectionListId") Integer collectionListId, @PathVariable("musicId") String musicId) {
        if (!collectionService.verify(collectionListId, musicId)) {
            return ResultUtils.success("音乐已存在");
        }
        if (collectionService.insertMusicToCollectionList(collectionListId, musicId)) {
            return ResultUtils.success("收藏成功");
        } else {
            return ResultUtils.failure("收藏失败");
        }
    }

    @PostMapping("/collection_list/{collectionListName}")
    public Result insertCollectionList(User user, @PathVariable("collectionListName") String collectionListName) {
        if (collectionService.insertCollectionList(collectionListName, user.getUserId())) {
            Integer collectionListId = collectionService.getCollectionListIdByCollectionListNameAndUserId(collectionListName, user.getUserId());
            return ResultUtils.success("创建成功", collectionListId);
        } else {
            return ResultUtils.failure("创建失败");
        }
    }

    @DeleteMapping("/collection_list/{collectionListId}")
    public Result deleteCollectionList(User user, @PathVariable("collectionListId") Integer collectionListId) {
        if (collectionService.deleteCollectionList(collectionListId)) {
            return ResultUtils.success("删除成功");
        } else {
            return ResultUtils.failure("删除失败");
        }
    }
}
