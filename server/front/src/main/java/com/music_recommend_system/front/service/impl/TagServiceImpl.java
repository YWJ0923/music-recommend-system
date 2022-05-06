package com.music_recommend_system.front.service.impl;

import com.music_recommend_system.front.dao.UserTagMapper;
import com.music_recommend_system.front.entity.Tag;
import com.music_recommend_system.front.service.TagService;
import com.music_recommend_system.front.dao.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ywj
 * @Date 2022/02/10
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagMapper tagMapper;
    @Autowired
    UserTagMapper userTagMapper;

    @Override
    public List<Tag> listTags() {
        return tagMapper.listTags();
    }

    @Override
    public boolean insertUserTags(Integer userId, List<Integer> tags) {
        return userTagMapper.insertUserTags(userId, tags) > 0;
    }
}
