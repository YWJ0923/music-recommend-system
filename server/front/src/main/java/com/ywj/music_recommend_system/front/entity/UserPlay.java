package com.ywj.music_recommend_system.front.entity;

/**
 * @Author ywj
 * @Date 2021/12/08
 */
public class UserPlay {
    private Integer userPlayId;
    private Integer userId;
    private String musicId;
    private Integer timeLength;
    private Boolean deleted;
    private String gmtCreate;
    private String gmtModified;

    public Integer getUserPlayId() {
        return userPlayId;
    }

    public void setUserPlayId(Integer userPlayId) {
        this.userPlayId = userPlayId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMusicId() {
        return musicId;
    }

    public void setMusicId(String musicId) {
        this.musicId = musicId;
    }

    public Integer getTimeLength() {
        return timeLength;
    }

    public void setTimeLength(Integer timeLength) {
        this.timeLength = timeLength;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(String gmtModified) {
        this.gmtModified = gmtModified;
    }
}
