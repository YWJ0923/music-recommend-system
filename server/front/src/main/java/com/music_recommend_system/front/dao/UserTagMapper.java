package com.music_recommend_system.front.dao;

import com.music_recommend_system.front.entity.UserTag;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTagMapper {
    /**
     * 插入用户喜好的标签
     * @param userId
     * @param tags
     * @return
     */
    int insertUserTags(@Param("userId") Integer userId, @Param("tags") List<Integer> tags);

    /**
     * 获取用户喜好的标签
     * @param userId
     * @return
     */
    List<UserTag> listUserTags(@Param("userId") Integer userId);
}
