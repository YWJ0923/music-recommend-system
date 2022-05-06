package com.music_recommend_system.front.entity;

/**
 * @Author ywj
 * @Date 2022/02/12
 */
public class Score {
    private Integer scoreId;
    private Integer userId;
    private String musicId;
    private Double score;

    public Score() {
        this.score = 0.0;
    }

    public Integer getScoreId() {
        return scoreId;
    }

    public void setScoreId(Integer scoreId) {
        this.scoreId = scoreId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMusicId() {
        return musicId;
    }

    public void setMusicId(String musicId) {
        this.musicId = musicId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
