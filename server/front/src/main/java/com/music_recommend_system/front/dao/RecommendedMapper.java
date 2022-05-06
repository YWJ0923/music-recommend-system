package com.music_recommend_system.front.dao;

import com.music_recommend_system.front.VO.MusicVO;
import com.music_recommend_system.front.entity.Recommended;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendedMapper {
    /**
     * 获取推荐过的歌
     * @param userId
     * @return
     */
    List<Recommended> listUserRecommendedMusics(Integer userId);

    /**
     * 记录推荐过的歌
     * @param list
     * @param userId
     * @return
     */
    int insertRecommendedMusics(@Param("list") List<MusicVO> list, @Param("userId") Integer userId);
}
