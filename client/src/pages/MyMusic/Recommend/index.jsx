import React, {useEffect, useState} from 'react';
import {Button, Table} from "antd";
import {playBarStore} from "../../../stores/playBarStore";
import {Link} from "react-router-dom";
import axios from "axios";
import {SERVER_URL} from "../../../utils/constant";
import {getToken} from "../../../utils/auth";
import PubSub from "pubsub-js";
import {showNotification} from "../../../utils/common";

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
    }
]

export default function Recommend(props) {
    const [recommend, setRecommend] = useState([]);

    useEffect(() => {
        axios({
            method: 'GET',
            url: SERVER_URL + 'recommend',
            headers: {
                Authorization: getToken()
            }
        }).then((response) => {
            if (response.data.status === 401) {
                PubSub.publish('logout');
                props.history.push('/');
                showNotification('请先登录');
            } else if (response.data.status === 200) {
                setRecommend(response.data.data);
            } else {
                props.history.push('/error');
            }
        });
        // eslint-disable-next-line
    }, []);

    return (
        <>
            <Table dataSource={recommend} columns={colomns} title={() => '每日推荐'} rowKey={record => record.musicId} pagination={false} />
        </>
    );
}