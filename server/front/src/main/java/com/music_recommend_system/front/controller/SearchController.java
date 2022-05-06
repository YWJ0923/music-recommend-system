package com.music_recommend_system.front.controller;

import com.music_recommend_system.front.VO.ArtistListVO;
import com.music_recommend_system.front.service.ArtistService;
import com.music_recommend_system.front.util.Result;
import com.music_recommend_system.front.VO.AlbumVO;
import com.music_recommend_system.front.VO.MusicVO;
import com.music_recommend_system.front.service.AlbumService;
import com.music_recommend_system.front.service.MusicService;
import com.music_recommend_system.front.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author ywj
 * @Date 2021/12/03
 */

/**
 * @ClassName SearchController
 * @Description 搜索Controller
 */
@RestController
public class SearchController {
    @Autowired
    MusicService musicService;
    @Autowired
    ArtistService artistService;
    @Autowired
    AlbumService albumService;

    @GetMapping("/search")
    /**
     * @Description: 根据类型和关键字搜索
     * @Param: [type, word]
     * @Return: com.music_recommend_system.front.util.Result
     */
    public Result search(@RequestParam("type") String type, @RequestParam("word") String word) {
        if ("music".equals(type)) {
            List<MusicVO> musicVOList = musicService.listMusicsByNameRegexp(word);
            return ResultUtils.success("搜索成功", musicVOList);
        } else if ("artist".equals(type)) {
            List<ArtistListVO> artistListVOList = artistService.listArtistsByArtistNameRegexp(word);
            return ResultUtils.success("搜索成功", artistListVOList);
        } else if ("album".equals(type)){
            List<AlbumVO> albumVOList = albumService.listAlbumsByAlbumNameRegexp(word);
            return ResultUtils.success("搜索成功", albumVOList);
        }
        return ResultUtils.failure("搜索失败");
    }
}
