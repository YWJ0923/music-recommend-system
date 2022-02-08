package com.ywj.music_recommend_system.back.service.impl;

import com.ywj.music_recommend_system.VO.front.AlbumVO;
import com.ywj.music_recommend_system.dao.AlbumMapper;
import com.ywj.music_recommend_system.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ywj
 * @Date 2021/11/29
 */
@Service
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    AlbumMapper albumMapper;

    @Override
    public AlbumVO getAlbumVOByAlbumId(String albumId) {
        return albumMapper.getAlbumVOByAlbumId(albumId);
    }

    @Override
    public List<AlbumVO> listAlbumsByAlbumNameRegexp(String albumName) {
        return albumMapper.listAlbumsByAlbumNameRegexp(albumName);
    }
}
