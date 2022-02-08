import React, { Suspense, useEffect, useState } from 'react'
import { Layout, Menu, Skeleton } from 'antd'
import axios from 'axios';
import PubSub from 'pubsub-js';
import {PlusCircleOutlined} from '@ant-design/icons';
import {renderRoutes} from 'react-router-config';
import { getToken } from '../../utils/auth';
import { SERVER_URL } from '../../utils/constant';
import {showNotification} from "../../utils/common";

const { Content, Sider } = Layout;
const {SubMenu} = Menu;

export default function MyMusic(props) {
    const [collectionLists, setCollectionLists] = useState([]);

    useEffect(() => {
        PubSub.subscribe('getCollectionList', getCollectionList);
        getCollectionList();  
        return () => {
            PubSub.unsubscribe('getCollectionList');
        }  // eslint-disable-next-line
    }, []);

    const handleClick = (obj) => {
        if (obj.key === 'recommend') {
            props.history.push('/my_music/recommend');
        } else if (obj.key === 'newList') {
            PubSub.publish('showNewCollectionListModal');
            getCollectionList();
        } else if (obj.key === 'ranking') {
            props.history.push('/my_music/ranking');
        } else {
            props.history.push('/my_music/collection_list/' + obj.key);
        }
    }

    const getCollectionList = () => {
        axios({
            method: 'GET',
            url: SERVER_URL + 'collection_list',
            headers: {
                Authorization: getToken()
            }
        }).then((response) => {
            if (response.data.status === 401) {
                PubSub.publish('logout');
                props.history.push('/');
                showNotification('请先登录');
            } else if (response.data.status === 200) {
                setCollectionLists(response.data.data);
            } else {
                props.history.push('/error');
            }
        });
    }

    return (
        <Layout style={{minHeight: '100vh'}}>
            <Sider>
                <Menu mode="inline" defaultSelectedKeys="ranking" style={{height: '100%'}} onClick={handleClick} defaultOpenKeys={['collection_list']}>
                    <Menu.Item key="ranking">听歌排行</Menu.Item>
                    <Menu.Item key="recommend">每日推荐</Menu.Item>
                    <SubMenu key="collection_list" title="我的歌单">
                        <Menu.Item key="newList" icon={<PlusCircleOutlined />}>创建新歌单</Menu.Item>
                        <Menu.Divider />
                        {collectionLists.map((list) => {
                            return (
                                <Menu.Item key={list.collectionListId} style={{paddingLeft: '10px', height: '50px'}}>
                                    <div style={{height: '40px', display: 'flex'}}>
                                        <img alt={list.collectionListName} src={list.collectionListImg} style={{width: '40px', height: '40px', marginRight: '10px'}} />
                                        <div style={{height: '40px'}}>
                                            <div style={{height: '20px', lineHeight: '15px', fontSize: '15px'}}>{list.collectionListName}</div>
                                            <div style={{height: '20px', lineHeight: '21px', fontSize: '13px', color: '#999'}}>{list.collectionListLength}首</div>
                                        </div>
                                    </div>
                                </Menu.Item>
                            );
                        })}
                    </SubMenu>
                </Menu>
            </Sider>
            <Content style={{ padding: '0', minHeight: 280}}>
                <Suspense fallback={<Skeleton />}>{renderRoutes(props.route.routes)}</Suspense>
            </Content>
        </Layout>
    );
}