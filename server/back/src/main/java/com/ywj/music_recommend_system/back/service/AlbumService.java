package com.ywj.music_recommend_system.back.service;

import com.ywj.music_recommend_system.VO.front.AlbumVO;

import java.util.List;

/**
 * @Author ywj
 * @Date 2021/11/29
 */
public interface AlbumService {
    AlbumVO getAlbumVOByAlbumId(String albumId);

    List<AlbumVO> listAlbumsByAlbumNameRegexp(String albumName);
}
