package com.music_recommend_system.front.dao;

import com.music_recommend_system.front.VO.AlbumVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author ywj
 * @Date 2021/11/29
 */
@Repository
public interface AlbumMapper {
    /**
     * 根据专辑id查询专辑
     * @param albumId
     * @return
     */
    AlbumVO getAlbumVOByAlbumId(String albumId);

    /**
     * 根据专辑名正则表达式查询专辑
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
