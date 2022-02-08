package com.ywj.music_recommend_system.front.controller;

import com.ywj.music_recommend_system.front.VO.AlbumVO;
import com.ywj.music_recommend_system.front.service.AlbumService;
import com.ywj.music_recommend_system.front.util.Result;
import com.ywj.music_recommend_system.front.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author ywj
 * @Date 2021/11/29
 */
@RestController
public class AlbumController {
    @Autowired
    AlbumService albumService;

    @GetMapping("/album/{albumId}")
    public Result getAlbumVOByAlbumId(@PathVariable("albumId") String albumId) {
        AlbumVO albumVO = albumService.getAlbumVOByAlbumId(albumId);
        return ResultUtils.success(albumVO);
    }

    @GetMapping("/latestAlbums")
    public Result listLatestAlbums() {
        return ResultUtils.success(albumService.listLatestAlbums());
    }
}
