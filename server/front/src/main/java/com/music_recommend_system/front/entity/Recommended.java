package com.music_recommend_system.front.entity;

/**
 * @author ywj
 * @date 2022/04/27
 * @className Recommend
 * @description
 */
public class Recommended {
    private Integer recommendedId;
    private String musicId;
    private Integer userId;

    public Integer getRecommendedId() {
        return recommendedId;
    }

    public void setRecommendedId(Integer recommendedId) {
        this.recommendedId = recommendedId;
    }

    public String getMusicId() {
        return musicId;
    }

    public void setMusicId(String musicId) {
        this.musicId = musicId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
