package com.music_recommend_system.front.controller;

import com.music_recommend_system.front.VO.ArtistVO;
import com.music_recommend_system.front.service.ArtistService;
import com.music_recommend_system.front.util.Result;
import com.music_recommend_system.front.VO.ArtistListVO;
import com.music_recommend_system.front.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author ywj
 * @Date 2021/11/26
 */

/**
 * @ClassName ArtistController
 * @Description 歌手Controller
 */
@RestController
public class ArtistController {
    @Autowired
    ArtistService artistService;

    @GetMapping("/artistList")
    /**
     * @Description: 根据歌手地区，歌手性别查询歌手
     * @Param: [artistLocation, artistType]
     * @Return: com.music_recommend_system.front.util.Result
     */
    public Result listArtists(@RequestParam("artistLocation") String artistLocation, @RequestParam(value = "artistType", required = false) String artistType) {
        List<ArtistListVO> artistListVOList = artistService.listArtistsByArtistLocationAndArtistType(artistLocation, artistType);
        return ResultUtils.success(artistListVOList);
    }

    @GetMapping("/artist/{artistId}")
    /**
     * @Description: 根据歌手id查询歌手
     * @Param: [artistId]
     * @Return: com.music_recommend_system.front.util.Result
     */
    public Result getArtistMusicByArtistId(@PathVariable("artistId") String artistId) {
        ArtistVO artistVO = artistService.listArtistMusicsByArtistId(artistId);
        return ResultUtils.success(artistVO);
    }

    @GetMapping("/artist/album/{artistId}")
    /**
     * @Description: 根据歌手id，查询他所有专辑
     * @Param: [artistId]
     * @Return: com.music_recommend_system.front.util.Result
     */
    public Result listArtistAlbumsByArtistId(@PathVariable("artistId") String artistId) {
        ArtistVO artistVO = artistService.listArtistAlbumsByArtistId(artistId);
        return ResultUtils.success(artistVO);
    }
}
