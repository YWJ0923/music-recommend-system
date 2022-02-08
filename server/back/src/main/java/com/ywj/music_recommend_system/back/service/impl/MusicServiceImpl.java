package com.ywj.music_recommend_system.back.service.impl;

import com.ywj.music_recommend_system.VO.MusicSearchVO;
import com.ywj.music_recommend_system.VO.MusicUpdateVO;
import com.ywj.music_recommend_system.VO.front.MusicVO;
import com.ywj.music_recommend_system.dao.MusicMapper;
import com.ywj.music_recommend_system.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ywj
 * @date 2021/11/09
 */
@Service
public class MusicServiceImpl implements MusicService {
    @Autowired
    MusicMapper musicMapper;

    @Override
    public List<MusicSearchVO> listMusicsByName(String name) {
        return musicMapper.listMusicsByName(name);
    }

    @Override
    public MusicUpdateVO getMusicUpdateVOByMusicId(String musicId) {
        MusicUpdateVO musicUpdateVO = musicMapper.getMusicUpdateVOByMusicId(musicId);
        return musicUpdateVO;
    }

    @Override
    public Boolean updatePlayTimesByMusicId(String musicId) {
        return musicMapper.updatePlayTimesByMusicId(musicId) == 1;
    }

    @Override
    public List<MusicVO> listMusicsByNameRegexp(String musicName) {
        return musicMapper.listMusicsByNameRegExp(musicName);
    }
}
