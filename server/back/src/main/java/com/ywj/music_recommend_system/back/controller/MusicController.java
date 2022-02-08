package com.ywj.music_recommend_system.back.controller;

import com.ywj.music_recommend_system.VO.MusicSearchVO;
import com.ywj.music_recommend_system.VO.MusicUpdateVO;
import com.ywj.music_recommend_system.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author ywj
 * @date 2021/11/09
 */
@Controller("backMusicController")
@RequestMapping("/admin")
public class MusicController {
    @Autowired
    MusicService musicService;

    @GetMapping("/musicManage")
    public String musicManage(HttpServletRequest request) {
        request.setAttribute("path", "music-manage");
        return "admin/music";
    }


    @GetMapping("/music")
    @ResponseBody
    /**
     * @Description: 根据歌名/歌手/专辑查询歌
     * @Param: [name]
     * @Return: java.util.List<com.ywj.music_recommend_system.VO.MusicSearchVO>
     */
    public List<MusicSearchVO> searchMusic(@RequestParam("musicName") String name) {
//        PageRequest pageRequest = new PageRequest(page, limit);
//        pageRequest.put("name", name);
        List<MusicSearchVO> musicSearchVOList = musicService.listMusicsByName(name);
        System.out.println(musicSearchVOList.size());
        return musicSearchVOList;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/music/{musicId}")
    @ResponseBody
    public MusicUpdateVO updateMusic(@PathVariable("musicId") String musicId) {
        System.out.println(musicId);
        return musicService.getMusicUpdateVOByMusicId(musicId);
    }
}
