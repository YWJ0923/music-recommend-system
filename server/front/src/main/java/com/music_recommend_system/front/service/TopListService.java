package com.music_recommend_system.front.service;

import com.music_recommend_system.front.VO.MusicVO;

import java.util.List;

/**
 * @Author ywj
 * @Date 2022/01/01
 */
public interface TopListService {
    /**
     * 根据类型获取排行榜
     * @param type
     * @return
     */
    List<MusicVO> getTopList(String type);
}
