package com.music_recommend_system.front.service;

/**
 * @Author ywj
 * @Date 2022/02/12
 */
public interface ScoreService {
    /**
     * 插入用户对该首歌评分
     * @param userId
     * @param musicId
     * @param score
     * @return
     */
    boolean insertScore(Integer userId, String musicId, Double score);
}
