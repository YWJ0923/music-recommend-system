import React, { useState, useEffect } from 'react'
import axios from 'axios';
import { Card, Layout, Typography, Menu, Table, Button, Row, Col } from 'antd';
import {Link} from 'react-router-dom';
import { SERVER_URL } from '../../utils/constant';
import { removeToken } from '../../utils/auth';
import {playBarStore} from "../../stores/playBarStore";

const { Paragraph } = Typography;
const {Content} = Layout;
const {Meta} = Card;

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
            return (<Button type="link" onClick={() => {
                playBarStore.addMusic(record);
            }}>{text}</Button>);
        },
        width: '500px'
    },
    {
        dataIndex: 'albumName',
        key: 'albumName',
        title: '专辑',
        render: (text, record, index) => {
            return (<Link to={`/album/${record.albumId}`}>{text}</Link>);
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

export default function Artist(props) {
    const [musicData, setMusicData] = useState({});
    const [albumData, setAlbumData] = useState({artistAlbumVOList: []});
    const [type, setType] = useState('');

    useEffect(() => {
        getMusic();  // eslint-disable-next-line
    }, []);

    const getMusic = () => {
        axios({
            method: 'GET',
            url: SERVER_URL + 'artist/' + props.match.params.artistId
        }).then((response) => {
            if (response.data.status === 200) {
                setMusicData(response.data.data);
                setType('music');
            } else {
                props.history.push('/error');
            }
        });
    }

    const getAlbum = () => {
        axios({
            method: 'GET',
            url: SERVER_URL + 'artist/album/' + props.match.params.artistId
        }).then((response) => {
            if (response.data.status === 2) {
                removeToken();
                props.history.push('/');
            } else if (response.data.status === 1) {
                setAlbumData(response.data.data);
                setType('album');
            }
        });
    }

    const handleClick = (obj) => {
        if (obj.key === 'music') {
            if (JSON.stringify(musicData) === '{}') {
                getMusic();
            }
            setType('music');
        } else {
            if (albumData.artistAlbumVOList.length === 0) {
                getAlbum();
            }
            setType('album');
        }
    }

    return (
        <>
            <div style={{display: 'flex', height: '200px'}}>
                <img alt={musicData.artistName} src={musicData.artistImg} />
                <Card title={musicData.artistName} style={{overflow: 'scroll', overflowX: 'hidden'}}>
                    <div style={{overflow: 'auto'}}>
                        <Paragraph ellipsis={{rows: 3, expandable: true, suffix: '...', symbol: '展开'}}>
                            {musicData.artistDescription}
                        </Paragraph>
                    </div>
                </Card>
            </div>
            <Content style={{ padding: '10px', minHeight: 280}}>
                <Menu mode="horizontal" defaultSelectedKeys="music" onClick={handleClick}>
                    <Menu.Item key="music">
                        单曲
                    </Menu.Item>
                    <Menu.Item key="album">
                        专辑
                    </Menu.Item>
                </Menu>
                {type === 'music' ? 
                <Table columns={colomns} dataSource={musicData.musicVOList} showHeader={false} pagination={{defaultPageSize: 20}} rowKey={record => record.musicId} />
                : <Row gutter={[10, 5]}>
                    {albumData.artistAlbumVOList.map((album) => {
                        return (<Col span={6}>
                            <Link to={`/album/${album.albumId}`}>
                                <Card hoverable cover={<img alt={album.albumName} src={album.albumImg} />}>
                                        <Meta title={album.albumName} style={{textAlign: 'center'}} description={album.albumPublishTime} />
                                </Card>
                            </Link>
                        </Col>)
                    })}
                </Row>}
            </Content>
        </>
    )
}
