import React, { Component } from 'react'
import { Layout, Menu } from 'antd';
import {Routes, Route, Link} from 'react-router-dom'
import './index.css';
import Music from '../Music';
const { Header, Content, Sider } = Layout;

export default class Home extends Component {
    render() {
        return (
            <Layout style={{minHeight: '100vh'}}>
                <Header className="header">
                    <div style={{color: 'white'}}>
                        <span className="fa fa-music"></span>&nbsp;
                        718音乐后台
                    </div>
                </Header>
                <Layout>
                    <Sider width={200} className="site-layout-background">
                        <Menu
                        mode="inline"
                        defaultSelectedKeys={['1']}
                        style={{ height: '100%', borderRight: 0 }}
                        >
                            <Menu.Item key="1" icon={<span className="fa fa-music"></span>}>
                                <Link to="/music">音乐管理</Link>
                            </Menu.Item>
                            <Menu.Item key="2" icon={<span className="fa fa-user"></span>}>用户管理</Menu.Item>
                        </Menu>
                    </Sider>
                    <Layout style={{ padding: '0 24px 24px' }}>
                        <Content
                        className="site-layout-background" 
                        style={{
                            padding: 24,
                            margin: 0,
                            minHeight: 280,
                        }}
                        >
                            <Routes>
                                <Route path="/music" element={<Music />} />
                            </Routes>
                        </Content>
                    </Layout>
                </Layout>
            </Layout>
        )
    }
}
