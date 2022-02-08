package com.ywj.music_recommend_system.front.service.impl;

import com.ywj.music_recommend_system.front.VO.MusicVO;
import com.ywj.music_recommend_system.front.dao.UserMapper;
import com.ywj.music_recommend_system.front.dao.UserPlayTimesMapper;
import com.ywj.music_recommend_system.front.entity.User;
import com.ywj.music_recommend_system.front.service.UserService;
import com.ywj.music_recommend_system.front.util.EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public User register(String username, String password, String nickname) {
        if (!StringUtils.hasLength(username) || !StringUtils.hasLength(password) || !StringUtils.hasLength(nickname)) {
            return null;
        }
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
        if (!StringUtils.hasLength(username) || !StringUtils.hasLength(password)) {
            return null;
        }
        password = EncryptUtils.encrypt(password);
        return userMapper.getUserByUserNameAndPassword(username, password);
    }

    @Override
    public User verify(Integer userId, String username) {
        if (userId == null || !StringUtils.hasLength(username)) {
            return null;
        }
        return userMapper.getUserByUserIdAndUsername(userId, username);
    }

    @Override
    public Boolean insertUserPlayTimes(Integer userId, String musicId, Integer timeLength) {
        return userPlayTimesMapper.insertUserPlayTimes(userId, musicId, timeLength) == 1;
    }

    @Override
    public List<MusicVO> listUserMusicsOrderByPlayTimes(Integer userId) {
        return userPlayTimesMapper.listUserMusicsOrderByPlayTimes(userId);
    }
}
