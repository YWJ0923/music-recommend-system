package com.music_recommend_system.front.dao;

import com.music_recommend_system.front.VO.MusicVO;
import com.music_recommend_system.front.entity.Score;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author ywj
 * @Date 2022/02/12
 */
@Repository
public interface ScoreMapper {
    /**
     * 插入评分
     * @param userId
     * @param musicId
     * @param score
     * @return
     */
    int insertScore(@Param("userId") Integer userId, @Param("musicId") String musicId, @Param("score") Double score);

    /**
     * 根据用户id和歌id查询评分
     * @param userId
     * @param musicId
     * @return
     */
    Score selectScoreByUserIdAndMusicId(@Param("userId") Integer userId, @Param("musicId") String musicId);

    /**
     * 修改评分
     * @param userId
     * @param musicId
     * @param score
     * @return
     */
    int updateScore(@Param("userId") Integer userId, @Param("musicId") String musicId, @Param("score") Double score);

    List<Score> listAllScores();

    /**
     * 根据用户id查询score表中的music_id，再联结查询歌的信息
     * @param userId
     * @return
     */
    List<MusicVO> listMusicsByUserId(Integer userId);
}
