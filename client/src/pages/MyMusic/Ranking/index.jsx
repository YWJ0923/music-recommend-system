import React, {useEffect, useState} from 'react';
import axios from 'axios';
import {Link} from 'react-router-dom';
import {Button, Table} from 'antd';
import { getToken } from '../../../utils/auth';
import { SERVER_URL } from '../../../utils/constant';
import {playBarStore} from "../../../stores/playBarStore";
import {showNotification} from "../../../utils/common";
import PubSub from "pubsub-js";

const colomns = [
    {
        key: 'index',
        render: (text, record, index) => {
            return index + 1;
        },
        width: '50px'
    },
    {
        dataIndex: 'musicName',
        key: 'musicName',
        title: '歌曲',
        ellipsis: true,
        render: (text, record, index) => {
            return (<Button type="link" onClick={() => {
                playBarStore.addMusic(record);
            }} style={{padding: '0'}}>{text}</Button>);
        },
        width: '300px'
    },
    {
        dataIndex: 'artistName',
        key: 'artistName',
        title: '歌手',
        ellipsis: true,
        render: (text, record, index) => {
            return (<Link to={`/artist/${record.artistId}`}>{text}</Link>);
        },
        width: '150px'
    },
    {
        dataIndex: 'albumName',
        key: 'albumName',
        title: '专辑',
        ellipsis: true,
        render: (text, record, index) => {
            return (<Link to={`/album/${record.albumId}`}>{text}</Link>);
        },
        width: '150px'
    },
    {
        dataIndex: 'userPlayTimes',
        key: 'userPlayTimes',
        title: '播放次数',
        render: (text) => {
            return text + '次';
        },
        width: '100px'
    }
]

export default function Ranking(props) {
    const [ranking, setRanking] = useState([]);
    
    useEffect(() => {
        axios({
            method: 'GET',
            url: SERVER_URL + 'user/music_ranking',
            headers: {
                Authorization: getToken()
            }
        }).then((response) => {
            if (response.data.status === 401) {
                PubSub.publish('logout');
                props.history.push('/');
                showNotification('请先登录');
            } else if (response.data.status === 200) {
                setRanking(response.data.data);
            } else {
                props.history.push('/error');
            }
        });
        // eslint-disable-next-line
    }, []);


    return (
        <>
            <Table dataSource={ranking} columns={colomns} title={() => '听歌排行'} rowKey={record => record.musicId} pagination={false} />
        </>
    );
}
