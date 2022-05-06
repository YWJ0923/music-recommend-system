package com.music_recommend_system.front.dao;

import com.music_recommend_system.front.VO.CollectionListVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author ywj
 * @Date 2021/12/02
 */
@Repository
public interface CollectionMapper {
    /**
     * 根据用户id查询他的歌单
     * @param userId
     * @return
     */
    List<CollectionListVO> listCollectionListsByUserId(Integer userId);

    /**
     * 歌插入歌单
     * @param collectionListId
     * @param musicId
     * @return
     */
    int insertMusicToCollectionList(Integer collectionListId, String musicId);

    /**
     * 根据歌单id，歌id查询歌是否在歌单中
     * @param collectionListId
     * @param musicId
     * @return
     */
    Integer getMusicCollectionListId(Integer collectionListId, String musicId);

    /**
     * 增加歌单
     * @param collectionListName
     * @param userId
     * @return
     */
    int insertCollectionList(String collectionListName, Integer userId);

    /**
     * 根据歌单名和用户id查询歌单
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
     * 修改歌单封面
     * @param collectionListId
     * @param musicId
     * @return
     */
    int updateCollectionListImg(Integer collectionListId, String musicId);

    /**
     * 删除歌单
     * @param collectionListId
     * @return
     */
    int deleteCollectionList(Integer collectionListId);
}
