package com.ywj.music_recommend_system.back.VO.front;

/**
 * @Author ywj
 * @Date 2021/11/29
 */
public class ArtistAlbumVO {
    private String albumId;
    private String albumName;
    private String albumImg;
    private String albumPublishTime;

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumImg() {
        return albumImg;
    }

    public void setAlbumImg(String albumImg) {
        this.albumImg = albumImg;
    }

    public String getAlbumPublishTime() {
        return albumPublishTime;
    }

    public void setAlbumPublishTime(String albumPublishTime) {
        this.albumPublishTime = albumPublishTime;
    }

    @Override
    public String toString() {
        return "ArtistAlbumVO{" +
                "albumId=" + albumId +
                ", albumName='" + albumName + '\'' +
                ", albumImg='" + albumImg + '\'' +
                ", albumPublishTime='" + albumPublishTime + '\'' +
                '}';
    }
}
