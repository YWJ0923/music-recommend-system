package com.music_recommend_system.front.dao;

import com.music_recommend_system.front.VO.MusicVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author ywj
 * @Date 2021/12/04
 */
@Repository
public interface UserPlayTimesMapper {
    /**
     * 插入用户播放记录
     * @param userId
     * @param musicId
     * @param timeLength
     * @return
     */
    int insertUserPlayTimes(Integer userId, String musicId, Integer timeLength);

    /**
     * 查询用户播放最多20首歌
     * @param userId
     * @return
     */
    List<MusicVO> listUserMusicsOrderByPlayTimes(Integer userId);
}
