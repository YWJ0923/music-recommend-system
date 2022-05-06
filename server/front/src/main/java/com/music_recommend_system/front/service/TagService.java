package com.music_recommend_system.front.service;

import com.music_recommend_system.front.entity.Tag;

import java.util.List;

/**
 * @Author ywj
 * @Date 2022/02/10
 */
public interface TagService {
    /**
     * 查询所有标签
     * @return
     */
    List<Tag> listTags();

    /**
     * 插入用户喜好标签
     * @param userId
     * @param tags
     * @return
     */
    boolean insertUserTags(Integer userId, List<Integer> tags);
}
