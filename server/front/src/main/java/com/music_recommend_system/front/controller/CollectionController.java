package com.music_recommend_system.front.controller;

import com.music_recommend_system.front.VO.CollectionListVO;
import com.music_recommend_system.front.entity.User;
import com.music_recommend_system.front.service.CollectionService;
import com.music_recommend_system.front.service.ScoreService;
import com.music_recommend_system.front.util.Result;
import com.music_recommend_system.front.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author ywj
 * @Date 2021/12/02
 */

/**
 * @ClassName CollectionController
 * @Description 用户歌单Controller
 */
@RestController
public class CollectionController {
    @Autowired
    CollectionService collectionService;
    @Autowired
    ScoreService scoreService;

    @GetMapping("/collection_list")
    /**
     * @Description: 查询用户所有歌单
     * @Param: [user]
     * @Return: com.music_recommend_system.front.util.Result
     */
    public Result listCollectionListsByUserId(User user) {
        List<CollectionListVO> collectionListVOList = collectionService.listCollectionlistsByUserId(user.getUserId());
        return ResultUtils.success("成功", collectionListVOList);
    }

    @GetMapping("/collection_list/{collectionListId}")
    /**
     * @Description: 根据歌单id，查询用户歌单
     * @Param: [user, collectionListId]
     * @Return: com.music_recommend_system.front.util.Result
     */
    public Result getCollectionListByCollectionListId(User user, @PathVariable("collectionListId") Integer collectionListId) {
        CollectionListVO collectionListVO = collectionService.getCollectionListByCollectionListId(collectionListId);
        return ResultUtils.success("成功", collectionListVO);
    }

    @PostMapping("/collection_list/{collectionListId}/{musicId}")
    /**
     * @Description: 将音乐添加到歌单中
     * @Param: [user, collectionListId, musicId]
     * @Return: com.music_recommend_system.front.util.Result
     */
    public Result insertMusicToCollectionList(User user, @PathVariable("collectionListId") Integer collectionListId, @PathVariable("musicId") String musicId) {
        scoreService.insertScore(user.getUserId(), musicId, 10.0);
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
    /**
     * @Description: 创建新的歌单
     * @Param: [user, collectionListName]
     * @Return: com.music_recommend_system.front.util.Result
     */
    public Result insertCollectionList(User user, @PathVariable("collectionListName") String collectionListName) {
        if (collectionService.insertCollectionList(collectionListName, user.getUserId())) {
            Integer collectionListId = collectionService.getCollectionListIdByCollectionListNameAndUserId(collectionListName, user.getUserId());
            return ResultUtils.success("创建成功", collectionListId);
        } else {
            return ResultUtils.failure("创建失败");
        }
    }

    @DeleteMapping("/collection_list/{collectionListId}")
    /**
     * @Description: 删除歌单
     * @Param: [user, collectionListId]
     * @Return: com.music_recommend_system.front.util.Result
     */
    public Result deleteCollectionList(User user, @PathVariable("collectionListId") Integer collectionListId) {
        if (collectionService.deleteCollectionList(collectionListId)) {
            return ResultUtils.success("删除成功");
        } else {
            return ResultUtils.failure("删除失败");
        }
    }
}
