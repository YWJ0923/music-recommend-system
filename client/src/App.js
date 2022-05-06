import React, {Suspense, useState, useEffect } from 'react';
import { Layout, Skeleton } from 'antd';
import {withRouter} from 'react-router-dom';
import { renderRoutes } from 'react-router-config';
import routes from './router';
import axios from 'axios';
import PlayBar from './components/PlayBar';
import MyHeader from './components/MyHeader';
import { getToken, removeToken } from './utils/auth';
import 'antd/dist/antd.min.css';
import './App.css';
import PubSub from "pubsub-js";
import {SERVER_URL} from "./utils/constant";


const { Content, Footer } = Layout;

function App(props) {
    const [user, setUser] = useState(undefined);

    // PubSub订阅和取消
    useEffect(() => {
        PubSub.subscribe('logout', logout);
        PubSub.subscribe('login', login);
        return () => {
            PubSub.unsubscribe('login');
            PubSub.unsubscribe('logout');
        }  // eslint-disable-next-line
    }, []);

    useEffect(() => {
        const token = getToken();
        if (token) {
            axios({
                method: 'GET',
                url: SERVER_URL + 'verify',
                headers: {
                    Authorization: token
                }
            }).then((response) => {
                if (response.data.status === 200) {
                    localStorage.setItem('token', response.data.token);
                    setUser(response.data.data);
                } else if (response.data.status === 401) {
                    removeToken();
                } else {
                    props.history.push('/error');
                }
            });
        }
    }, []);

    const logout = () => {
        removeToken();
        setUser(undefined);
    }

    const login = (message, user) => {
        setUser(user);
    }

    return (
        <>
            <Layout className="layout" style={{minHeight: '100vh'}}>
                <MyHeader user={user} />
                <Content style={{ width: '1000px', margin: '0 auto'}}>
                    <div className="site-layout-content">
                        <Suspense fallback={<Skeleton />}>
                            {renderRoutes(routes)}
                        </Suspense>
                    </div>
                </Content>
                <Footer style={{ textAlign: 'center', height: '150px' }}>718音乐 ©2021 Created by YWJ</Footer>
            </Layout>
            <PlayBar user={user} />
        </>
    );
}

export default withRouter(App);