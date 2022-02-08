package com.ywj.music_recommend_system.front.service;

import com.ywj.music_recommend_system.front.VO.MusicVO;

import java.util.List;

/**
 * @Author ywj
 * @Date 2022/01/01
 */
public interface TopListService {
    List<MusicVO> getTopList(String type);
}
