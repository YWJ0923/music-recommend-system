package com.ywj.music_recommend_system.front.service;

import com.ywj.music_recommend_system.front.VO.MusicVO;
import com.ywj.music_recommend_system.front.entity.User;

import java.util.List;

/**
 * @Author ywj
 * @Date 2021/11/30
 */
public interface UserService {
    User login(String username, String password);

    User register(String username, String password, String nickname);

    User verify(Integer userId, String username);

    Boolean insertUserPlayTimes(Integer userId, String musicId, Integer timeLength);

    List<MusicVO> listUserMusicsOrderByPlayTimes(Integer userId);
}
