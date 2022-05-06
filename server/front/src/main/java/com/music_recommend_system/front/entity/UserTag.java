package com.music_recommend_system.front.entity;

/**
 * @author ywj
 * @date 2022/04/27
 * @className UserTag
 * @description
 */
public class UserTag {
    private Integer userTagId;
    private Integer tagId;
    private Integer userId;

    public Integer getUserTagId() {
        return userTagId;
    }

    public void setUserTagId(Integer userTagId) {
        this.userTagId = userTagId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
