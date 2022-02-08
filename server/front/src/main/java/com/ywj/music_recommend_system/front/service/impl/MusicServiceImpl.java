package com.ywj.music_recommend_system.front.service.impl;
import com.ywj.music_recommend_system.front.VO.MusicVO;
import com.ywj.music_recommend_system.front.dao.MusicMapper;
import com.ywj.music_recommend_system.front.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author ywj
 * @date 2021/11/09
 */
@Service
public class MusicServiceImpl implements MusicService {
    @Autowired
    MusicMapper musicMapper;
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public Boolean updatePlayTimesByMusicId(String musicId) {
        return musicMapper.updatePlayTimesByMusicId(musicId) == 1;
    }

    @Override
    public List<MusicVO> listMusicsByNameRegexp(String musicName) {
        return musicMapper.listMusicsByNameRegExp(musicName);
    }

    @Override
    public List<MusicVO> listLatestMusics() {
        List<MusicVO> latestMusics = (List<MusicVO>) redisTemplate.opsForValue().get("latestMusics");
        if (latestMusics == null) {
            latestMusics = musicMapper.listLatestMusics();
            redisTemplate.opsForValue().set("latestMusics", latestMusics, 1, TimeUnit.DAYS);
        }
        return latestMusics;
    }
}
