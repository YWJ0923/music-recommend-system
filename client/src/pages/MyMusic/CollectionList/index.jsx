import React, {useEffect, useState} from 'react';
import {Link} from 'react-router-dom'
import axios from 'axios';
import {Image, Button, Table, Card, Modal} from 'antd';
import { SERVER_URL } from '../../../utils/constant';
import { getToken } from '../../../utils/auth';
import {DeleteOutlined, ExclamationCircleOutlined, PlayCircleOutlined} from "@ant-design/icons";
import PubSub from "pubsub-js";
import {playBarStore} from "../../../stores/playBarStore";
import {showNotification} from "../../../utils/common";
import {useLocalObservable} from "mobx-react";

const {confirm} = Modal;
const collectionListColomns = [
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
        dataIndex: 'timeLength',
        key: 'timeLength',
        title: '时长',
        render: (text) => {
            text = parseInt(text);
            text /= 1000;
            var min = Math.floor(text / 60);
            if (min < 10) {
                min = '0' + min;
            }
            var sec = Math.floor(text % 60);
            if (sec < 10) {
                sec = '0' + sec;
            }
            return (<span>{min + ':' + sec}</span>);
        },
        width: '100px'
    }
];

export default function CollectionList(props) {
    const {addPlayList} = useLocalObservable(() => playBarStore);

    const [collectionList, setCollectionList] = useState({});

    useEffect(() => {
        axios({
            method: 'GET',
            url: SERVER_URL + 'collection_list/' + props.match.params.collection_list_id,
            headers: {
                Authorization: getToken()
            }
        }).then((response) => {
            if (response.data.status === 401) {
                PubSub.publish('logout');
                props.history.push('/');
                showNotification('请先登录');
            } else if (response.data.status === 200) {
                setCollectionList(response.data.data);
            } else {
                props.history.push('/error');
            }
        });
        // eslint-disable-next-line
    }, [props]);

    const deleteCollectionList = (collectionList) => {
        confirm({
            title: '确认删除',
            icon: <ExclamationCircleOutlined />,
            okText: '确认',
            cancelText: '取消',
            onOk() {
                axios({
                    method: 'DELETE',
                    url: SERVER_URL + 'collection_list/' + collectionList.collectionListId,
                    headers: {
                        Authorization: getToken()
                    }
                }).then((response) => {
                    if (response.data.status === 401) {
                        PubSub.publish('logout');
                        props.history.push('/');
                        showNotification('请先登录');
                    } else if (response.data.status === 200) {
                        PubSub.publish('getCollectionList');
                        props.history.replace('/my_music');
                    } else {
                        props.history.replace('/error');
                    }
                });
            }
        });
    }

    const playCollectionList = (collectionList) => {
        console.log(collectionList);
        if (collectionList.musicVOList.length > 0) {
            addPlayList(collectionList.musicVOList);
        }
    }

    return (
        <>
            <div style={{display: 'flex', height: '200px', backgroundColor: 'white', padding: '0 10px'}}>
                <Image alt={collectionList.collectionListName} src={collectionList.collectionListImg} width="200px"/>
                <div style={{padding: '0 10px'}}>
                    <Card title={collectionList.collectionListName} style={{width: '100%', height: '120px'}} bordered={false}>
                        {collectionList.createDate} 创建
                    </Card>
                    <div style={{padding: '40px 20px'}}>
                        <Button icon={<PlayCircleOutlined />} type="primary" onClick={() => playCollectionList(collectionList)} style={{marginRight: '20px'}}>播放全部</Button>
                        <Button icon={<DeleteOutlined />} type="danger" onClick={() =>deleteCollectionList(collectionList)}>删除歌单</Button>
                    </div>
                </div>
            </div>
            <Table columns={collectionListColomns} dataSource={collectionList.musicVOList} rowKey={record => record.musicId} />
        </>
    );
}