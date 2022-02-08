package com.ywj.music_recommend_system.front.dao;

import com.ywj.music_recommend_system.front.VO.ArtistListVO;
import com.ywj.music_recommend_system.front.VO.ArtistVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author ywj
 * @Date 2021/11/26
 */
@Repository
public interface ArtistMapper {
    List<ArtistListVO> listArtistsByArtistLocationAndArtistType(Integer artistLocation, Integer artistType);
    List<ArtistListVO> listHotArtists();
    ArtistVO listArtistMusicsByArtistId(String artistId);
    ArtistVO listArtistAlbumsByArtistId(String artistId);
    List<ArtistListVO> listArtistsByArtistNameRegexp(String artistName);
}
