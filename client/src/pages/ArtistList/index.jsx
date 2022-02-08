import { Layout, Menu, Row, Col, Card } from 'antd'
import React, { useState, useEffect } from 'react';
import {Link} from 'react-router-dom'
import axios from 'axios';
import { SERVER_URL } from '../../utils/constant';

const { Content, Sider } = Layout;
const {SubMenu} = Menu;
const {Meta} = Card;

export default function ArtistList(props) {
    const [data, setData] = useState([]);
    const [selectedKeys, setSelectedKeys] = useState('artist_location=hot');

    useEffect(() => {
        // const {artistLocation, artistType} = props.match.params;
        const params = new URLSearchParams(props.location.search);
        const artistLocation = params.get('artist_location');
        const artistType = params.get('artist_type');
        if (artistLocation === 'hot') {
            axios({
                method: 'GET',
                url: SERVER_URL + 'artistList',
                params: {
                    artistLocation: artistLocation
                }
            }).then((response) => {
                if (response.data.status === 200) {
                    setData(response.data.data);
                    setSelectedKeys('artist_location=hot');
                } else {
                    props.history.push('/error');
                }
            });
        } else {
            axios({
                method: 'GET',
                url: SERVER_URL + 'artistList',
                params: {
                    artistLocation: artistLocation,
                    artistType: artistType
                }
            }).then((response) => {
                if (response.data.status === 200) {
                    setData(response.data.data);
                    setSelectedKeys(`artist_location=${artistLocation}&artist_type=${artistType}`);
                } else {
                    props.history.push('/error');
                }
            });
        }
    }, [props])

    const handleClick = (obj) => {
        props.history.push(`/artist_list?${obj.key}`);
    }

    return (
        <Layout>
            <Sider style={{width: 250}}>
                <Menu mode="inline" style={{height: '100%'}} onClick={handleClick} defaultOpenKeys={['0', '1', '2']} selectedKeys={selectedKeys}>
                    <Menu.Item key="artist_location=hot">热门歌手</Menu.Item>
                    <SubMenu key="0" title="华语">
                        <Menu.Item key="artist_location=0&artist_type=0">华语男歌手</Menu.Item>
                        <Menu.Item key="artist_location=0&artist_type=1">华语女歌手</Menu.Item>
                        <Menu.Item key="artist_location=0&artist_type=2">华语组合/乐队</Menu.Item>
                    </SubMenu>
                    <SubMenu key="1" title="欧美">
                        <Menu.Item key="artist_location=1&artist_type=0">欧美男歌手</Menu.Item>
                        <Menu.Item key="artist_location=1&artist_type=1">欧美女歌手</Menu.Item>
                        <Menu.Item key="artist_location=1&artist_type=2">欧美组合/乐队</Menu.Item>
                    </SubMenu>
                    <SubMenu key="2" title="日韩">
                        <Menu.Item key="artist_location=2&artist_type=0">日韩男歌手</Menu.Item>
                        <Menu.Item key="artist_location=2&artist_type=1">日韩女歌手</Menu.Item>
                        <Menu.Item key="artist_location=2&artist_type=2">日韩组合/乐队</Menu.Item>
                    </SubMenu>
                </Menu>
            </Sider>
            <Content style={{ paddingLeft: '10px', minHeight: 280}}>
                <Row gutter={[10, 5]}>
                    {data.map((artist, index) => {
                        return (<Col span={6} key={index}>
                            <Link to={`/artist/${artist.artistId}`}>
                                    <Card hoverable cover={<img alt={artist.artistName} src={artist.artistImg} />}>
                                            <Meta title={artist.artistName} style={{textAlign: 'center'}} />
                                    </Card>
                            </Link>
                        </Col>)
                    })}
                </Row>
            </Content>
        </Layout>
    );
}
