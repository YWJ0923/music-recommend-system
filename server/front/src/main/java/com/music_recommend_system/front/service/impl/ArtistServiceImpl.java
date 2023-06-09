package com.music_recommend_system.front.service.impl;

import com.music_recommend_system.front.VO.ArtistListVO;
import com.music_recommend_system.front.VO.ArtistVO;
import com.music_recommend_system.front.dao.ArtistMapper;
import com.music_recommend_system.front.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author ywj
 * @Date 2021/11/26
 */
@Service
public class ArtistServiceImpl implements ArtistService {
    @Autowired
    ArtistMapper artistMapper;
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public List<ArtistListVO> listArtistsByArtistLocationAndArtistType(String artistLocation, String artistType) {
        // 用redis
        List<ArtistListVO> artistListVOList;
        if ("hot".equals(artistLocation)) {
            artistListVOList = (List<ArtistListVO>) redisTemplate.opsForValue().get("artistList:hot");
            if (artistListVOList == null) {
                artistListVOList =  artistMapper.listHotArtists();
                redisTemplate.opsForValue().set("artistList:hot", artistListVOList, 1, TimeUnit.DAYS);
            }
        } else {
            artistListVOList = (List<ArtistListVO>) redisTemplate.opsForValue().get("artistList:" + artistLocation + artistType);
            if (artistListVOList == null) {
                artistListVOList = artistMapper.listArtistsByArtistLocationAndArtistType(Integer.parseInt(artistLocation), Integer.parseInt(artistType));
                redisTemplate.opsForValue().set("artistList:" + artistLocation + artistType, artistListVOList, 1, TimeUnit.DAYS);
            }
        }
        return artistListVOList;
//        // 不用redis
//        if ("hot".equals(artistLocation)) {
//            return artistMapper.listHotArtists();
//        } else {
//            return artistMapper.listArtistsByArtistLocationAndArtistType(Integer.parseInt(artistLocation), Integer.parseInt(artistType));
//        }
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
