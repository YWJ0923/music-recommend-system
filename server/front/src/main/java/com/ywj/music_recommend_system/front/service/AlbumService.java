package com.ywj.music_recommend_system.front.service;

import com.ywj.music_recommend_system.front.VO.AlbumVO;

import java.util.List;

/**
 * @Author ywj
 * @Date 2021/11/29
 */
public interface AlbumService {
    AlbumVO getAlbumVOByAlbumId(String albumId);

    List<AlbumVO> listAlbumsByAlbumNameRegexp(String albumName);

    List<AlbumVO> listLatestAlbums();
}
