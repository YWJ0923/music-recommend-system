package com.ywj.music_recommend_system.back.dao;

import com.ywj.music_recommend_system.VO.front.MusicVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author ywj
 * @Date 2021/12/04
 */
@Repository
public interface UserPlayTimesMapper {
    int insertUserPlayTimes(Integer userId, String musicId);

    List<MusicVO> listUserMusicsOrderByPlayTimes(Integer userId);
}
