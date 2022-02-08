package com.ywj.music_recommend_system.back.VO.front;

/**
 * @Author ywj
 * @Date 2021/11/26
 */
public class ArtistListVO {
    private String artistId;
    private String artistName;
    private String artistImg;

    @Override
    public String toString() {
        return "ArtistListVO{" +
                "artistId=" + artistId +
                ", artistName='" + artistName + '\'' +
                ", artistImg='" + artistImg + '\'' +
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
}
