package com.music_recommend_system.front.controller;

import com.music_recommend_system.front.VO.AlbumVO;
import com.music_recommend_system.front.service.AlbumService;
import com.music_recommend_system.front.util.Result;
import com.music_recommend_system.front.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author ywj
 * @Date 2021/11/29
 */

/**
 * @ClassName AlbumController
 * @Description 专辑Controller
 */
@RestController
public class AlbumController {
    @Autowired
    AlbumService albumService;

    @GetMapping("/album/{albumId}")
    /**
     * @Description: 根据专辑id查询专辑
     * @Param: [albumId]
     * @Return: com.music_recommend_system.front.util.Result
     */
    public Result getAlbumVOByAlbumId(@PathVariable("albumId") String albumId) {
        AlbumVO albumVO = albumService.getAlbumVOByAlbumId(albumId);
        return ResultUtils.success(albumVO);
    }


    @GetMapping("/latestAlbums")
    /**
     * @Description: 查询最新专辑
     * @Param: []
     * @Return: com.music_recommend_system.front.util.Result
     */
    public Result listLatestAlbums() {
        return ResultUtils.success(albumService.listLatestAlbums());
    }
}
