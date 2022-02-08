package com.ywj.music_recommend_system.back.dao;

import com.ywj.music_recommend_system.VO.front.CollectionListVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author ywj
 * @Date 2021/12/02
 */
@Repository
public interface CollectionMapper {
    List<CollectionListVO> listCollectionListsByUserId(Integer userId);

    int insertMusicToCollectionList(Integer collectionListId, String musicId);

    Integer getMusicCollectionListId(Integer collectionListId, String musicId);

    int insertCollectionList(String collectionListName, Integer userId);

    Integer getCollectionListIdByCollectionListNameAndUserId(String collectionListName, Integer userId);

    CollectionListVO getCollectionListByCollectionListId(Integer collectionListId);

    int updateCollectionListImg(Integer collectionListId, String musicId);

    int deleteCollectionList(Integer collectionListId);
}
