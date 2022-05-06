package com.music_recommend_system.front.dao;

import com.music_recommend_system.front.VO.ArtistListVO;
import com.music_recommend_system.front.VO.ArtistVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author ywj
 * @Date 2021/11/26
 */
@Repository
public interface ArtistMapper {
    /**
     * 根据歌手地区，歌手类型查询歌手
     * @param artistLocation
     * @param artistType
     * @return
     */
    List<ArtistListVO> listArtistsByArtistLocationAndArtistType(Integer artistLocation, Integer artistType);

    /**
     * 查询播放量最多的28位歌手
     * @return
     */
    List<ArtistListVO> listHotArtists();

    /**
     * 根据歌手id查询他的歌
     * @param artistId
     * @return
     */
    ArtistVO listArtistMusicsByArtistId(String artistId);

    /**
     * 根据歌手id查询他的专辑
     * @param artistId
     * @return
     */
    ArtistVO listArtistAlbumsByArtistId(String artistId);

    /**
     * 根据歌手姓名正则表达式查询歌手
     * @param artistName
     * @return
     */
    List<ArtistListVO> listArtistsByArtistNameRegexp(String artistName);
}
