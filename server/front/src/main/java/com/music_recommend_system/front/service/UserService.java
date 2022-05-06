package com.music_recommend_system.front.service;

import com.music_recommend_system.front.VO.MusicVO;
import com.music_recommend_system.front.entity.User;

import java.util.List;

/**
 * @Author ywj
 * @Date 2021/11/30
 */
public interface UserService {
    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    User login(String username, String password);

    /**
     * 注册
     * @param username
     * @param password
     * @param nickname
     * @return
     */
    User register(String username, String password, String nickname);

    /**
     * 验证
     * @param userId
     * @param username
     * @return
     */
    User verify(Integer userId, String username);

    /**
     * 插入用户播放记录
     * @param userId
     * @param musicId
     * @param timeLength
     * @return
     */
    Boolean insertUserPlayTimes(Integer userId, String musicId, Integer timeLength);

    /**
     * 查询用户播放最多20首歌
     * @param userId
     * @return
     */
    List<MusicVO> listUserMusicsOrderByPlayTimes(Integer userId);

    /**
     * 查询用户总数
     * @return
     */
    int countUser();
}
