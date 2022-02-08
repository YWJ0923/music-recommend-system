package com.ywj.music_recommend_system.back.service;

import com.ywj.music_recommend_system.VO.front.ArtistListVO;
import com.ywj.music_recommend_system.VO.front.ArtistVO;

import java.util.List;

/**
 * @Author ywj
 * @Date 2021/11/26
 */
public interface ArtistService {
    List<ArtistListVO> listArtistsByArtistLocationAndArtistType(String artistLocation, String artistType);

    List<ArtistListVO> listArtistsByArtistNameRegexp(String artistName);

    ArtistVO listArtistMusicsByArtistId(String artistId);

    ArtistVO listArtistAlbumsByArtistId(String artistId);
}
