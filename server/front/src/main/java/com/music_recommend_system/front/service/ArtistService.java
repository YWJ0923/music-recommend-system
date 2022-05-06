package com.music_recommend_system.front.service;

import com.music_recommend_system.front.VO.ArtistListVO;
import com.music_recommend_system.front.VO.ArtistVO;

import java.util.List;

/**
 * @Author ywj
 * @Date 2021/11/26
 */
public interface ArtistService {
    /**
     * 根据歌手地区，歌手性别查询歌手
     * @param artistLocation
     * @param artistType
     * @return
     */
    List<ArtistListVO> listArtistsByArtistLocationAndArtistType(String artistLocation, String artistType);

    /**
     * 根据歌手姓名正则表达式查询歌手
     * @param artistName
     * @return
     */
    List<ArtistListVO> listArtistsByArtistNameRegexp(String artistName);

    /**
     * 根据歌手id查询歌手的歌
     * @param artistId
     * @return
     */
    ArtistVO listArtistMusicsByArtistId(String artistId);

    /**
     * 根据歌手id查询歌手专辑
     * @param artistId
     * @return
     */
    ArtistVO listArtistAlbumsByArtistId(String artistId);
}
