package com.ywj.music_recommend_system.back.VO.front;

import java.util.List;

/**
 * @Author ywj
 * @Date 2021/12/02
 */
public class CollectionListVO {
    private Integer collectionListId;
    private String collectionListName;
    private Integer userId;
    private String createDate;
    private String collectionListImg;
    private Integer collectionListLength;
    private List<MusicVO> musicVOList;

    public Integer getCollectionListLength() {
        return collectionListLength;
    }

    public void setCollectionListLength(Integer collectionListLength) {
        this.collectionListLength = collectionListLength;
    }

    public Integer getCollectionListId() {
        return collectionListId;
    }

    public void setCollectionListId(Integer collectionListId) {
        this.collectionListId = collectionListId;
    }

    public String getCollectionListName() {
        return collectionListName;
    }

    public void setCollectionListName(String collectionListName) {
        this.collectionListName = collectionListName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCollectionListImg() {
        return collectionListImg;
    }

    public void setCollectionListImg(String collectionListImg) {
        this.collectionListImg = collectionListImg;
    }

    public List<MusicVO> getMusicVOList() {
        return musicVOList;
    }

    public void setMusicVOList(List<MusicVO> musicVOList) {
        this.musicVOList = musicVOList;
    }
}
