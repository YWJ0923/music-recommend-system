package com.music_recommend_system.front.service;

import com.music_recommend_system.front.VO.AlbumVO;

import java.util.List;

/**
 * @Author ywj
 * @Date 2021/11/29
 */
public interface AlbumService {
    /**
     * 根据专辑id查询专辑
     * @param albumId
     * @return
     */
    AlbumVO getAlbumVOByAlbumId(String albumId);

    /**
     * 根据关键字正则表达式查询专辑
     * @param albumName
     * @return
     */
    List<AlbumVO> listAlbumsByAlbumNameRegexp(String albumName);

    /**
     * 查询最新专辑
     * @return
     */
    List<AlbumVO> listLatestAlbums();
}
