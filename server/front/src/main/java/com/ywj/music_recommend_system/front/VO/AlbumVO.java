package com.ywj.music_recommend_system.front.VO;

import java.util.List;

/**
 * @Author ywj
 * @Date 2021/11/29
 */
public class AlbumVO {
    private String albumId;
    private String albumName;
    private String albumImg;
    private String albumDescription;
    private String albumPublishTime;
    private String artistId;
    private String artistName;
    private List<MusicVO> musicVOList;

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

    public String getAlbumDescription() {
        return albumDescription;
    }

    public void setAlbumDescription(String albumDescription) {
        this.albumDescription = albumDescription;
    }

    public String getAlbumPublishTime() {
        return albumPublishTime;
    }

    public void setAlbumPublishTime(String albumPublishTime) {
        this.albumPublishTime = albumPublishTime;
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

    public List<MusicVO> getMusicVOList() {
        return musicVOList;
    }

    public void setMusicVOList(List<MusicVO> musicVOList) {
        this.musicVOList = musicVOList;
    }

    @Override
    public String toString() {
        return "AlbumVO{" +
                "albumId='" + albumId + '\'' +
                ", albumName='" + albumName + '\'' +
                ", albumImg='" + albumImg + '\'' +
                ", albumDescription='" + albumDescription + '\'' +
                ", albumPublishTime='" + albumPublishTime + '\'' +
                ", artistId='" + artistId + '\'' +
                ", artistName='" + artistName + '\'' +
                ", musicVOList=" + musicVOList +
                '}';
    }
}
