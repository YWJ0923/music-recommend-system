package com.ywj.music_recommend_system.back.service;

import com.ywj.music_recommend_system.VO.front.CollectionListVO;

import java.util.List;

/**
 * @Author ywj
 * @Date 2021/12/02
 */
public interface CollectionService {
    List<CollectionListVO> listCollectionlistsByUserId(Integer userId);

    Boolean insertMusicToCollectionList(Integer collectionListId, String musicId);

    /**
     * @Description: 验证音乐是否已存在歌单中
     * @Param:
     * @Return:
     */
    Boolean verify(Integer collectionListId, String musicId);

    Boolean insertCollectionList(String collectionListName, Integer userId);

    Integer getCollectionListIdByCollectionListNameAndUserId(String collectionListName, Integer userId);

    CollectionListVO getCollectionListByCollectionListId(Integer collectionListId);

    Boolean deleteCollectionList(Integer collectionListId);
}
