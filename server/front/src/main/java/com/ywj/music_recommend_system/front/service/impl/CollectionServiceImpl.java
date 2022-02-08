package com.ywj.music_recommend_system.front.service.impl;

import com.ywj.music_recommend_system.front.VO.CollectionListVO;
import com.ywj.music_recommend_system.front.dao.CollectionMapper;
import com.ywj.music_recommend_system.front.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ywj
 * @Date 2021/12/02
 */
@Service
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    CollectionMapper collectionMapper;

    @Override
    public List<CollectionListVO> listCollectionlistsByUserId(Integer userId) {
        return collectionMapper.listCollectionListsByUserId(userId);
    }

    /**
     * @Description: 将歌加入歌单同时更新歌单图片为专辑图片
     * @Param:
     * @Return:
     */
    @Override
    public Boolean insertMusicToCollectionList(Integer collectionListId, String musicId) {
        if (collectionMapper.insertMusicToCollectionList(collectionListId, musicId) == 1) {
            return collectionMapper.updateCollectionListImg(collectionListId, musicId) == 1;
        } else {
            return false;
        }
    }

    @Override
    public Boolean verify(Integer collectionListId, String musicId) {
        Integer musicCollectionListId = collectionMapper.getMusicCollectionListId(collectionListId, musicId);
        return musicCollectionListId == null;
    }

    @Override
    public Boolean insertCollectionList(String collectionListName, Integer userId) {
        if (collectionMapper.insertCollectionList(collectionListName, userId) == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Integer getCollectionListIdByCollectionListNameAndUserId(String collectionListName, Integer userId) {
        return collectionMapper.getCollectionListIdByCollectionListNameAndUserId(collectionListName, userId);
    }

    @Override
    public CollectionListVO getCollectionListByCollectionListId(Integer collectionListId) {
        return collectionMapper.getCollectionListByCollectionListId(collectionListId);
    }

    @Override
    public Boolean deleteCollectionList(Integer collectionListId) {
        return collectionMapper.deleteCollectionList(collectionListId) == 1;
    }
}
