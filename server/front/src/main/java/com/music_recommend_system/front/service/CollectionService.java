package com.music_recommend_system.front.service;

import com.music_recommend_system.front.VO.CollectionListVO;

import java.util.List;

/**
 * @Author ywj
 * @Date 2021/12/02
 */
public interface CollectionService {
    /**
     * 查询用户所有歌单
     * @param userId
     * @return
     */
    List<CollectionListVO> listCollectionlistsByUserId(Integer userId);

    /**
     * 歌插入歌单同时更新歌单图片为专辑图片
     * @param collectionListId
     * @param musicId
     * @return
     */
    Boolean insertMusicToCollectionList(Integer collectionListId, String musicId);

    /**
     * 查询歌是否已在歌单中
     * @param collectionListId
     * @param musicId
     * @return
     */
    Boolean verify(Integer collectionListId, String musicId);

    /**
     * 增加新歌单
     * @param collectionListName
     * @param userId
     * @return
     */
    Boolean insertCollectionList(String collectionListName, Integer userId);

    /**
     * 根据歌单名字和用户id查询歌单
     * @param collectionListName
     * @param userId
     * @return
     */
    Integer getCollectionListIdByCollectionListNameAndUserId(String collectionListName, Integer userId);

    /**
     * 根据歌单id查询歌单
     * @param collectionListId
     * @return
     */
    CollectionListVO getCollectionListByCollectionListId(Integer collectionListId);

    /**
     * 删除歌单
     * @param collectionListId
     * @return
     */
    Boolean deleteCollectionList(Integer collectionListId);
}
