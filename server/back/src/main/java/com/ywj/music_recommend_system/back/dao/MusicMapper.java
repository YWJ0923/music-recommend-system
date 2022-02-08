package com.ywj.music_recommend_system.back.dao;

import com.ywj.music_recommend_system.VO.MusicSearchVO;
import com.ywj.music_recommend_system.VO.MusicUpdateVO;
import com.ywj.music_recommend_system.VO.front.MusicVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ywj
 * @date 2021/11/09
 */
@Repository
public interface MusicMapper {
    List<MusicSearchVO> listMusicsByName(String name);

    int countMusicByMusicName(String name);

    int countMusicByArtistName(String name);

    MusicUpdateVO getMusicUpdateVOByMusicId(String musicId);

    int updatePlayTimesByMusicId(String musicId);

    List<MusicVO> listMusicsByNameRegExp(String musicName);
}
