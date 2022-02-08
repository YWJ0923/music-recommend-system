import {makeObservable, observable, action, toJS} from 'mobx';
import {getPlayUrl} from "../utils/play";

class PlayBarStore {
    playList = [];

    index = 0;

    playUrl = '';

    constructor() {
        makeObservable(this, {
            playList: observable,
            index: observable,
            playUrl: observable,
            setPlayList: action,
            addMusic: action,
            setIndex: action,
            setPlayUrl: action,
            addPlayList: action
        })
    }

    setPlayList = (newPlayList) => {
        this.playList = newPlayList;
    }

    setIndex = (newIndex) => {
        this.index = newIndex;
    }

    addMusic = (newMusic) => {
        // console.log(toJS(this.playList));
        getPlayUrl(toJS(newMusic));
        let newPlayList = this.playList.filter(music => music.musicId !== newMusic.musicId);
        newPlayList.push(newMusic);
        this.setPlayList(newPlayList);
        this.setIndex(newPlayList.length - 1);
        // console.log(toJS(this.playList));
    }

    addPlayList = (playList) => {
        let newPlayList = toJS(this.playList);
        getPlayUrl(playList[0]);
        if (newPlayList.length > 0) {
            for (let i = 0; i < playList.length; i++) {
                newPlayList = newPlayList.filter(music => music.musicId !== playList[i].musicId);
            }
            let newIndex = newPlayList.length;
            // console.log(newIndex);
            newPlayList = newPlayList.concat(playList);
            // console.log(newPlayList);
            this.setPlayList(newPlayList);
            this.setIndex(newIndex);
        } else {
            this.setPlayList(playList);
            this.setIndex(0);
        }
    }

    setPlayUrl = (newPlayUrl) => {
        this.playUrl = newPlayUrl;
    }
}

export const playBarStore = new PlayBarStore();


