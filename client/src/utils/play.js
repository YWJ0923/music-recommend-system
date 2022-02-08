import axios from "axios";
import {playBarStore} from "../stores/playBarStore";

export function getPlayUrl(record) {
    axios({
        method: 'GET',
        url: 'http://localhost:3001/kugou',
        params: {
            r: 'play/getdata',
            hash: record.musicId,
            album_id: record.albumId,
            mid: '39f0247247ae8d32fa08e059894d0361',
            appid: '1014',
            _: new Date().getTime()
        },
        withCredentials: true
    }).then((response) => {
        // console.log(response.data);
        playBarStore.setPlayUrl(response.data.data.play_url);
    });
}