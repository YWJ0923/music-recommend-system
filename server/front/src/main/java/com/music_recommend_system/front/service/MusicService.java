package com.music_recommend_system.front.service;

import com.music_recommend_system.front.VO.MusicVO;

import java.util.List;

/**
 * @author ywj
 * @date 2021/11/09
 */
public interface MusicService {
    /**
     * 根据歌名正则表达式查询歌
     * @param musicName
     * @return
     */
    List<MusicVO> listMusicsByNameRegexp(String musicName);

    /**
     * 根据歌id更新播放量
     * @param musicId
     * @return
     */
    Boolean updatePlayTimesByMusicId(String musicId);

    /**
     * 查询最新歌
     * @return
     */
    List<MusicVO> listLatestMusics();
}
