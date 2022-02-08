package com.ywj.music_recommend_system.front.service;

import com.ywj.music_recommend_system.front.VO.MusicVO;

import java.util.List;

/**
 * @author ywj
 * @date 2021/11/09
 */
public interface MusicService {
    List<MusicVO> listMusicsByNameRegexp(String musicName);

    Boolean updatePlayTimesByMusicId(String musicId);

    List<MusicVO> listLatestMusics();
}
