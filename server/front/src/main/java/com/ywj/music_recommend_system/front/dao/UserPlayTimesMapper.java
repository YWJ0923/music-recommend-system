package com.ywj.music_recommend_system.front.dao;

import com.ywj.music_recommend_system.front.VO.MusicVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author ywj
 * @Date 2021/12/04
 */
@Repository
public interface UserPlayTimesMapper {
    int insertUserPlayTimes(Integer userId, String musicId, Integer timeLength);

    List<MusicVO> listUserMusicsOrderByPlayTimes(Integer userId);
}
