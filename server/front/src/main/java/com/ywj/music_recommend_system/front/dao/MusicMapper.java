package com.ywj.music_recommend_system.front.dao;

import com.ywj.music_recommend_system.front.VO.MusicVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ywj
 * @date 2021/11/09
 */
@Repository
public interface MusicMapper {
    int countMusicByMusicName(String name);

    int countMusicByArtistName(String name);

    int updatePlayTimesByMusicId(String musicId);

    List<MusicVO> listMusicsByNameRegExp(String name);

    List<MusicVO> getTopList();

    List<MusicVO> getTopListByArtistLocation(Integer artistLocation);

    List<MusicVO> listLatestMusics();
}
