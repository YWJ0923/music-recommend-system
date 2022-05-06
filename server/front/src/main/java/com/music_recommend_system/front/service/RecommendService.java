package com.music_recommend_system.front.service;

import com.music_recommend_system.front.VO.MusicVO;
import com.music_recommend_system.front.entity.User;
import com.music_recommend_system.front.entity.UserTag;
import com.music_recommend_system.front.util.Result;

import java.util.List;

public interface RecommendService {
    /**
     * 推荐歌曲
     * @param userId
     * @return
     */
    List<MusicVO> listRecommendMusics(Integer userId);
}
