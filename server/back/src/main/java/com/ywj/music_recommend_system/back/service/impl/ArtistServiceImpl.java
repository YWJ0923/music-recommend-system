package com.ywj.music_recommend_system.back.service.impl;

import com.ywj.music_recommend_system.VO.front.ArtistListVO;
import com.ywj.music_recommend_system.VO.front.ArtistVO;
import com.ywj.music_recommend_system.dao.ArtistMapper;
import com.ywj.music_recommend_system.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ywj
 * @Date 2021/11/26
 */
@Service
public class ArtistServiceImpl implements ArtistService {
    @Autowired
    ArtistMapper artistMapper;

    @Override
    public List<ArtistListVO> listArtistsByArtistLocationAndArtistType(String artistLocation, String artistType) {
        if ("hot".equals(artistLocation)) {
            return artistMapper.listHotArtists();
        }
        return artistMapper.listArtistsByArtistLocationAndArtistType(Integer.parseInt(artistLocation), Integer.parseInt(artistType));
    }

    @Override
    public List<ArtistListVO> listArtistsByArtistNameRegexp(String artistName) {
        return artistMapper.listArtistsByArtistNameRegexp(artistName);
    }

    @Override
    public ArtistVO listArtistMusicsByArtistId(String artistId) {
        return artistMapper.listArtistMusicsByArtistId(artistId);
    }

    @Override
    public ArtistVO listArtistAlbumsByArtistId(String artistId) {
        return artistMapper.listArtistAlbumsByArtistId(artistId);
    }
}
