package com.music_recommend_system.front.dao;

import com.music_recommend_system.front.entity.Tag;
import com.music_recommend_system.front.entity.UserTag;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author ywj
 * @Date 2022/02/10
 */
@Repository
public interface TagMapper {
    /**
     * 查询所有标签
     * @return
     */
    List<Tag> listTags();


}
