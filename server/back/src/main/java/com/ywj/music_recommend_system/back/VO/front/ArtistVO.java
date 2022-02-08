package com.ywj.music_recommend_system.back.VO.front;

import java.util.List;

/**
 * @Author ywj
 * @Date 2021/11/27
 */
public class ArtistVO {
    private String artistId;
    private String artistName;
    private String artistImg;
    private String artistDescription;
    private List<MusicVO> musicVOList;
    private List<ArtistAlbumVO> artistAlbumVOList;

    public List<ArtistAlbumVO> getArtistAlbumVOList() {
        return artistAlbumVOList;
    }

    public void setArtistAlbumVOList(List<ArtistAlbumVO> artistAlbumVOList) {
        this.artistAlbumVOList = artistAlbumVOList;
    }

    @Override
    public String toString() {
        return "ArtistVO{" +
                "artistId=" + artistId +
                ", artistName='" + artistName + '\'' +
                ", artistImg='" + artistImg + '\'' +
                ", artistDescription='" + artistDescription + '\'' +
                ", musicVOList=" + musicVOList +
                '}';
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

    public String getArtistImg() {
        return artistImg;
    }

    public void setArtistImg(String artistImg) {
        this.artistImg = artistImg;
    }

    public String getArtistDescription() {
        return artistDescription;
    }

    public void setArtistDescription(String artistDescription) {
        this.artistDescription = artistDescription;
    }

    public List<MusicVO> getMusicVOList() {
        return musicVOList;
    }

    public void setMusicVOList(List<MusicVO> musicVOList) {
        this.musicVOList = musicVOList;
    }
}
