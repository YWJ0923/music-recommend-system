package com.ywj.music_recommend_system.back.dao;

import com.ywj.music_recommend_system.VO.front.AlbumVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author ywj
 * @Date 2021/11/29
 */
@Repository
public interface AlbumMapper {
    AlbumVO getAlbumVOByAlbumId(String albumId);

    List<AlbumVO> listAlbumsByAlbumNameRegexp(String albumName);
}
