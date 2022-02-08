package com.ywj.music_recommend_system.back.service;

import com.ywj.music_recommend_system.VO.front.MusicVO;
import com.ywj.music_recommend_system.entity.User;

import java.util.List;

/**
 * @Author ywj
 * @Date 2021/11/30
 */
public interface UserService {
    User login(String username, String password);

    User getUserInfoByUsername(String username);

    User register(String username, String password, String nickname);

    User verify(Integer userId, String username);

    Boolean insertUserPlayTimes(Integer userId, String musicId);

    List<MusicVO> listUserMusicsOrderByPlayTimes(Integer userId);
}
