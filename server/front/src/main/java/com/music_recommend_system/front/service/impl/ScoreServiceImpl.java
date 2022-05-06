package com.music_recommend_system.front.service.impl;

import com.music_recommend_system.front.dao.ScoreMapper;
import com.music_recommend_system.front.service.ScoreService;
import com.music_recommend_system.front.entity.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author ywj
 * @Date 2022/02/12
 */
@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    ScoreMapper scoreMapper;

    @Override
    public boolean insertScore(Integer userId, String musicId, Double score) {
        Score s = scoreMapper.selectScoreByUserIdAndMusicId(userId, musicId);
        if (s != null) {
            return scoreMapper.updateScore(userId, musicId, Math.max(score, s.getScore())) > 0;
        } else {
            return scoreMapper.insertScore(userId, musicId, score) > 0;
        }
    }
}
