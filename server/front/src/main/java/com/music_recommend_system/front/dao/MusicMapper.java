package com.music_recommend_system.front.dao;

import com.music_recommend_system.front.VO.MusicVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ywj
 * @date 2021/11/09
 */
@Repository
public interface MusicMapper {
    /**
     * 统计该歌名有几首歌
     * @param name
     * @return
     */
    int countMusicByMusicName(String name);

    /**
     * 统计该歌手有几首歌
     * @param name
     * @return
     */
    int countMusicByArtistName(String name);

    /**
     * 修改播放量
     * @param musicId
     * @return
     */
    int updatePlayTimesByMusicId(String musicId);

    /**
     * 根据歌名正则表达式查询歌
     * @param name
     * @return
     */
    List<MusicVO> listMusicsByNameRegExp(String name);

    /**
     * 查询播放量最高的20首歌
     * @return
     */
    List<MusicVO> getTopList20();

    /**
     * 查询播放量最高的100首歌
     * @return
     */
    List<MusicVO> getTopList100();

    /**
     * 根据歌手地区查询播放量最高20首歌
     * @param artistLocation
     * @return
     */
    List<MusicVO> getTopListByArtistLocation(Integer artistLocation);

    /**
     * 查询最新歌
     * @return
     */
    List<MusicVO> listLatestMusics();
}
