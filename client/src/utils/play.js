import axios from "axios";
import {playBarStore} from "../stores/playBarStore";
import {KUGOU_URL} from "./constant";

axios.defaults.withCredentials = true;

export function getPlayUrl(record) {
    axios({
        method: 'GET',
        url: KUGOU_URL,
        params: {
            r: 'play/getdata',
            hash: record.musicId,
            album_id: record.albumId,
            mid: '39f0247247ae8d32fa08e059894d0361',
            appid: '1014',
            _: new Date().getTime()
        },
        // headers: {
        //     cookie: 'kg_mid=39f0247247ae8d32fa08e059894d0361; kg_dfid=3x8KjG0Z8M2U2M53fL2FxHb9; kg_dfid_collect=d41d8cd98f00b204e9800998ecf8427e; KugooID=1534449652; a_id=1014; KuGoo=KugooID=1534449652&KugooPwd=490DE731557209844A6F65DBD92B3AF1&NickName=%u539f%u5473%u9e21&Pic=http://imge.kugou.com/kugouicon/165/20190817/20190817235035526577.jpg&RegState=1&RegFrom=&t=5807e5ff2d7eaedd7a78dcb0a15c65ff165a94862b5c530b4df36994722fd517&t_ts=1646971730&t_key=&a_id=1014&ct=1646971730&UserName=%u0031%u0033%u0038%u0035%u0037%u0038%u0036%u0030%u0032%u0033%u0034;'
        // },
        withCredentials: true
    }).then((response) => {
        // console.log(response.data);
        playBarStore.setPlayUrl(response.data.data.play_url);
    });
}