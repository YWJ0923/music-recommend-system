package com.ywj.music_recommend_system.back.service.impl;

import com.ywj.music_recommend_system.VO.front.MusicVO;
import com.ywj.music_recommend_system.dao.UserMapper;
import com.ywj.music_recommend_system.dao.UserPlayTimesMapper;
import com.ywj.music_recommend_system.entity.User;
import com.ywj.music_recommend_system.service.UserService;
import com.ywj.music_recommend_system.util.EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ywj
 * @Date 2021/11/30
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserPlayTimesMapper userPlayTimesMapper;

    @Override
    public User register(String username, String password, String nickname) {
        if (userMapper.getUserByUsername(username) != null) {
            return null;
        } else {
            password = EncryptUtils.encrypt(password);
            if (userMapper.insertUser(username, password, nickname) == 1) {
                return userMapper.getUserByUsername(username);
            } else {
                return null;
            }
        }
    }

    @Override
    public User login(String username, String password) {
        password = EncryptUtils.encrypt(password);
        return userMapper.getUserByUserNameAndPassword(username, password);
    }

    @Override
    public User verify(Integer userId, String username) {
        return userMapper.getUserByUserIdAndUsername(userId, username);
    }

    @Override
    public User getUserInfoByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public Boolean insertUserPlayTimes(Integer userId, String musicId) {
        return userPlayTimesMapper.insertUserPlayTimes(userId, musicId) == 1;
    }

    @Override
    public List<MusicVO> listUserMusicsOrderByPlayTimes(Integer userId) {
        return userPlayTimesMapper.listUserMusicsOrderByPlayTimes(userId);
    }
}
