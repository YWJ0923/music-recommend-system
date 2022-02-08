import axios from 'axios'
import React, { useEffect, useState } from 'react';
import { Image, Button, Table, Layout } from 'antd';
import { Link } from 'react-router-dom';
import { SERVER_URL } from '../../utils/constant';
import {playBarStore} from "../../stores/playBarStore";

const {Content} = Layout;
const colomns = [
    {
        key: 'index',
        render: (text,record,index) => {return index + 1;}
    },
    {
        dataIndex: 'musicName',
        key: 'musicName',
        title: '歌曲',
        render: (text, record, index) => {
            return (<Button type="link" style={{padding: '0'}} onClick={() => {
                playBarStore.addMusic(record);
            }}>{text}</Button>);
        },
        width: '500px'
    },
    {
        dataIndex: 'artistName',
        key: 'artistName',
        title: '歌手',
        render: (text, record) => {
            return (<Link to={`/artist/${record.artistId}`}>{text}</Link>);
        },
        width: '400px'
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

export default function Album(props) {
    const [data, setData] = useState([]);

    useEffect(() => {
        axios({
            method: 'GET',
            url: SERVER_URL + 'album/' + props.match.params.albumId,
        }).then((response) => {
            if (response.data.status === 200) {
                setData(response.data.data);
            } else {
                props.history.push('/error');
            }
        });
    }, [props])

    return (
        <>
            <div className="info1" style={{display: 'flex'}}>
                <Image src={data.albumImg} />
                <div className="info2" style={{marginLeft: '50px'}}>
                    <h2>{data.albumName}</h2>
                    <p>歌手：{data.artistName}</p>
                    <p>发行日期：{data.albumPublishTime}</p>
                </div>
            </div>
            <Content style={{padding: '10px'}}>
                <h2 style={{margin: '20px 0'}}>包含歌曲列表</h2>
                <Table columns={colomns} dataSource={data.musicVOList} pagination={{defaultPageSize: 20}} rowKey="musicId" />
            </Content>
        </>
    );
}
