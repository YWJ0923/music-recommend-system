import axios from 'axios';
import React, { useState, useEffect } from 'react'
import {Menu, Table, Button, Row, Col, Card} from 'antd';
import { Link } from 'react-router-dom';
import { SERVER_URL } from '../../utils/constant';
import {playBarStore} from "../../stores/playBarStore";

const {Meta} = Card;
const musicColomns = [
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
        width: '200px'
    },
    {
        dataIndex: 'albumName',
        key: 'albumName',
        title: '专辑',
        ellipsis: true,
        render: (text, record, index) => {
            return (<Link to={`/album/${record.albumId}`}>{text}</Link>);
        },
        width: '200px'
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

const albumColomns = [
    {
        dataIndex: 'albumName',
        key: 'albumName',
        title: '专辑',
        ellipsis: true,
        render: (text, record, index) => {
            return (
            <Link to={`/album/${record.albumId}`} style={{display: 'flex'}}>
                <img alt={text} style={{width: '80px', height: '80px'}} src={record.albumImg} />
                <div style={{margin: 'auto 20px'}}>{text}</div>
            </Link>
            );
        },
        width: '400px'
    },
    {
        dataIndex: 'artistName',
        key: 'artistName',
        title: '歌手',
        ellipsis: true,
        render: (text, record, index) => {
            return (<Link to={`/artist/${record.artistId}`}>{text}</Link>);
        },
        width: '200px'
    },
    {
        dataIndex: 'albumPublishTime',
        key: 'albumPublishTime',
        title: '发行时间',
        width: '100px'
    }
];

export default function Search(props) {
    const [data, setData] = useState([]);
    const [type, setType] = useState('');
    const [word, setWord] = useState('');

    useEffect(() => {
        const params = new URLSearchParams(props.location.search);
        const type = params.get('type');
        const word = params.get('word');
        axios({
            method: 'GET',
            url: SERVER_URL + 'search',
            params: {
                type: type,
                word: word
            }
        }).then((response) => {
            if (response.data.status === 200) {
                setType(type);
                setWord(word);
                setData(response.data.data);
            }
        });
    }, [props]);

    const handleClick = (obj) => {
        props.history.push(`/search?type=${obj.key}&word=${word}`);
    }

    return (
        <>
            <Menu onClick={handleClick} mode="horizontal" selectedKeys={type}>
                <Menu.Item key="music">单曲</Menu.Item>
                <Menu.Item key="artist">歌手</Menu.Item>
                <Menu.Item key="album">专辑</Menu.Item>
            </Menu>
            {type === 'music' && <Table dataSource={data} columns={musicColomns} pagination={{defaultPageSize: 20}} rowKey={record => record.musicId} />}
            {type === 'artist' && (data[0] ? 
            <Row gutter={[10, 5]}>
                {data.map((artist, index) => {
                    return (<Col span={4} key={index}>
                        <Link to={`/artist/${artist.artistId}`}>
                                <Card hoverable cover={<img alt={artist.artistName} src={artist.artistImg} />}>
                                        <Meta title={artist.artistName} style={{textAlign: 'center'}} />
                                </Card>
                        </Link>
                    </Col>)
                })}
            </Row>
            : <Table dataSource={[]} showHeader={false} />)}
            {type === 'album' && <Table dataSource={data} columns={albumColomns} pagination={{defaultPageSize: 20}} rowKey={record => record.albumId}/>}
        </>
    );
}
