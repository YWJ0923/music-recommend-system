import React, {useEffect, useRef, useState} from 'react';
import {Button, Layout, Menu, Table} from "antd";
import {Link} from "react-router-dom";
import axios from "axios";
import {playBarStore} from "../../stores/playBarStore";
import {SERVER_URL} from "../../utils/constant";

const { Content, Sider } = Layout;
const {SubMenu} = Menu;

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
            }} style={{padding: '0'}}>
                <img alt={text} src={record.albumImg} style={{height: '50px', width: '50px', marginRight: '10px'}} />
                {text}
            </Button>);
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
]

export default function TopList(props) {
    const [data, setData] = useState([]);
    const [selectedKeys, setSelectedKeys] = useState('type=total');
    const [title, setTitle] = useState('总榜');

    const titleArr = ['华语榜', '欧美榜', '日韩榜'];

    useEffect(() => {
        const params = new URLSearchParams(props.location.search);
        const type = params.get('type');
        axios({
            method: 'GET',
            url: SERVER_URL + 'topList',
            params: {
                type: type
            }
        }).then((response) => {
            if (response.data.status === 200) {
                setData(response.data.data);
                setSelectedKeys(`type=${type}`);
                setTitle(type === 'total' ? '总榜' : titleArr[parseInt(type)]);
            } else {
                props.history.push('/error');
            }
        });  // eslint-disable-next-line
    }, [props]);

    const handleClick = (obj) => {
        props.history.push(`/top_list?${obj.key}`);
    }

    const tableTitle = () => {
        return (
            <>
                <div style={{fontWeight: 'bold', fontSize: '25px', display: 'inline', marginRight: '20px'}}>{title}</div>
                <div style={{fontSize: '15px', display: 'inline'}}>每日更新</div>
            </>

        )
    }

    return (
        <Layout>
            <Sider style={{width: 250}}>
                <Menu mode="inline" defaultSelectedKeys="type=total" style={{height: '100%'}} onClick={handleClick} defaultOpenKeys={['0', '1', '2']} selectedKeys={selectedKeys}>
                    <Menu.Item key="type=total">总榜</Menu.Item>
                    <Menu.Item key="type=0">华语榜</Menu.Item>
                    <Menu.Item key="type=1">欧美榜</Menu.Item>
                    <Menu.Item key="type=2">日韩榜</Menu.Item>
                </Menu>
            </Sider>
            <Content style={{ minHeight: 280}}>
                <Table dataSource={data} columns={colomns} rowKey={record => record.musicId} pagination={false} title={tableTitle} />
            </Content>
        </Layout>
    );
}