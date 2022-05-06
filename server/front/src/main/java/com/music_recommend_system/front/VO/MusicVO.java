package com.music_recommend_system.front.VO;

/**
 * @Author ywj
 * @Date 2021/11/27
 */
public class MusicVO {
    private String musicId;
    private String musicName;
    private String artistId;
    private String artistName;
    private String albumId;
    private String albumName;
    private String albumImg;
    private Integer timeLength;
    private Integer userPlayTimes;
    private Integer totalPlayTimes;

    public Integer getUserPlayTimes() {
        return userPlayTimes;
    }

    public void setUserPlayTimes(Integer userPlayTimes) {
        this.userPlayTimes = userPlayTimes;
    }

    public Integer getTotalPlayTimes() {
        return totalPlayTimes;
    }

    public void setTotalPlayTimes(Integer totalPlayTimes) {
        this.totalPlayTimes = totalPlayTimes;
    }

    public String getMusicId() {
        return musicId;
    }

    public void setMusicId(String musicId) {
        this.musicId = musicId;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

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

    public Integer getTimeLength() {
        return timeLength;
    }

    public void setTimeLength(Integer timeLength) {
        this.timeLength = timeLength;
    }

    @Override
    public String toString() {
        return "MusicVO{" +
                "musicId='" + musicId + '\'' +
                ", musicName='" + musicName + '\'' +
                ", artistId='" + artistId + '\'' +
                ", artistName='" + artistName + '\'' +
                ", albumId='" + albumId + '\'' +
                ", albumName='" + albumName + '\'' +
                ", albumImg='" + albumImg + '\'' +
                ", timeLength=" + timeLength +
                '}';
    }
}
