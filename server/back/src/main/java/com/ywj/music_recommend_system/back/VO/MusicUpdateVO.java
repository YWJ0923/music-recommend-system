package com.ywj.music_recommend_system.back.VO;

/**
 * @author ywj
 * @date 2021/11/10
 */
public class MusicUpdateVO {
    private Integer musicId;
    private String musicName;
    private String artistName;
    private String albumName;
    private String lyric;
    private String musicUrl;

    @Override
    public String toString() {
        return "MusicUpdateVO{" +
                "musicId='" + musicId + '\'' +
                ", musicName='" + musicName + '\'' +
                ", artistName='" + artistName + '\'' +
                ", albumName='" + albumName + '\'' +
                ", lyric='" + lyric + '\'' +
                ", musicUrl='" + musicUrl + '\'' +
                '}';
    }

    public Integer getMusicId() {
        return musicId;
    }

    public void setMusicId(Integer musicId) {
        this.musicId = musicId;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }
}
