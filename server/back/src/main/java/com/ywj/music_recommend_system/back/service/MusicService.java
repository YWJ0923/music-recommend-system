package com.ywj.music_recommend_system.back.service;

import com.ywj.music_recommend_system.VO.MusicSearchVO;
import com.ywj.music_recommend_system.VO.MusicUpdateVO;
import com.ywj.music_recommend_system.VO.front.MusicVO;

import java.util.List;

/**
 * @author ywj
 * @date 2021/11/09
 */
public interface MusicService {
    List<MusicSearchVO> listMusicsByName(String name);

    List<MusicVO> listMusicsByNameRegexp(String musicName);

    MusicUpdateVO getMusicUpdateVOByMusicId(String musicId);

    Boolean updatePlayTimesByMusicId(String musicId);
}
