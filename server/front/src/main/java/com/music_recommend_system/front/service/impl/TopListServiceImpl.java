package com.music_recommend_system.front.service.impl;

import com.music_recommend_system.front.VO.MusicVO;
import com.music_recommend_system.front.dao.MusicMapper;
import com.music_recommend_system.front.service.TopListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author ywj
 * @Date 2022/01/01
 */
@Service
public class TopListServiceImpl implements TopListService {
    @Autowired
    MusicMapper musicMapper;
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public List<MusicVO> getTopList(String type) {
        // 用redis
        List<MusicVO> topList = (List<MusicVO>) redisTemplate.opsForValue().get("topList:" + type);
        if (topList == null) {
            if ("total".equals(type)) {
                topList = musicMapper.getTopList20();
            } else {
                topList = musicMapper.getTopListByArtistLocation(Integer.parseInt(type));
            }
            redisTemplate.opsForValue().set("topList:" + type, topList, 1, TimeUnit.DAYS);
        }
        return topList;
//        // 不用redis
//        if ("total".equals(type)) {
//            return musicMapper.getTopList();
//        } else {
//            return musicMapper.getTopListByArtistLocation(Integer.parseInt(type));
//        }
    }
}
