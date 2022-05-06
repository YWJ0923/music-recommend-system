package com.music_recommend_system.front.service.impl;

import com.music_recommend_system.front.service.AlbumService;
import com.music_recommend_system.front.VO.AlbumVO;
import com.music_recommend_system.front.dao.AlbumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author ywj
 * @Date 2021/11/29
 */
@Service
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    AlbumMapper albumMapper;
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public AlbumVO getAlbumVOByAlbumId(String albumId) {
        return albumMapper.getAlbumVOByAlbumId(albumId);
    }

    @Override
    public List<AlbumVO> listAlbumsByAlbumNameRegexp(String albumName) {
        return albumMapper.listAlbumsByAlbumNameRegexp(albumName);
    }

    @Override
    public List<AlbumVO> listLatestAlbums() {
        // 用redis
        List<AlbumVO> latestAlbums = (List<AlbumVO>) redisTemplate.opsForValue().get("latestAlbums");
        if (latestAlbums == null) {
            latestAlbums = albumMapper.listLatestAlbums();
            redisTemplate.opsForValue().set("latestAlbums", latestAlbums, 1, TimeUnit.DAYS);
        }
        return latestAlbums;
//        // 不用redis
//        return albumMapper.listLatestAlbums();
    }
}
