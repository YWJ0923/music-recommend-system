package com.ywj.music_recommend_system.back.VO;

/**
 * @author ywj
 * @date 2021/11/09
 */
public class MusicSearchVO {
    private String musicId;
    private String musicName;
    private String artistName;
    private String albumName;
    private Integer playTimes;

    @Override
    public String toString() {
        return "MusicSearchVO{" +
                "musicId='" + musicId + '\'' +
                ", musicName='" + musicName + '\'' +
                ", artistName='" + artistName + '\'' +
                ", albumName='" + albumName + '\'' +
                ", playTimes=" + playTimes +
                '}';
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

    public Integer getPlayTimes() {
        return playTimes;
    }

    public void setPlayTimes(Integer playTimes) {
        this.playTimes = playTimes;
    }
}
