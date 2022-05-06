import React, { useEffect, useRef, useState } from 'react'
import { Button, Menu, Input, Modal, Form, Checkbox, Dropdown, Layout } from 'antd';
import {withRouter} from 'react-router-dom';
import 'antd/dist/antd.min.css';
import { LockOutlined, UserOutlined, LogoutOutlined } from '@ant-design/icons';
import axios from 'axios';
import PubSub from 'pubsub-js';
import {SERVER_URL} from "../../utils/constant";
import {getToken} from "../../utils/auth";

const { Header } = Layout;
const {Search} = Input;

function MyHeader(props) {
    const [isLoginModalVisible, setIsLoginModalVisible] = useState(false);
    const [isRegisterModalVisible, setIsRegisterModalVisible] = useState(false);
    const [isTagModalVisible, setIsTagModalVisible] = useState(false);
    const [tagList, setTagList] = useState([]);

    useEffect(() => {
        PubSub.subscribe('showLoginModal', showLoginModal);
        return () => {
            PubSub.unsubscribe('showLoginModal');
        }  // eslint-disable-next-line
    }, []);
    
    const alert1 = useRef(null);
    const alert2 = useRef(null);

    const handleClickUserMenu = (obj) => {
        if (obj.key === 'logout') {
            PubSub.publish('logout');
        }
    }

    const userMenu = () => {
        return (
            <Menu onClick={handleClickUserMenu}>
                <Menu.Item key="home" icon={<UserOutlined />}>
                    我的主页
                </Menu.Item>
                <Menu.Item key="logout" icon={<LogoutOutlined />}>
                    退出
                </Menu.Item>
            </Menu>
        );
    }

    const showLoginModal = () => {
        closeRegisterModal();
        setIsLoginModalVisible(true);
    }

    const closeLoginModal = () => {
        setIsLoginModalVisible(false);
    }

    const showRegisterModal = () => {
        closeLoginModal();
        setIsRegisterModalVisible(true);
    }

    const closeRegisterModal = () => {
        setIsRegisterModalVisible(false);
    }

    const listTags = () => {
        axios({
            method: 'GET',
            url: SERVER_URL + 'tag'
        }).then((response) => {
            if (response.data.status === 200) {
                let tagList = response.data.data;
                let tags = [];
                for (let i = 0; i < tagList.length; i++) {
                    tags.push({
                        label: tagList[i].tagName,
                        value: tagList[i].tagId
                    });
                }
                // console.log(tags);
                setTagList(tags);
            }
        });
    }

    const showTagModal = () => {
        listTags();
        setIsTagModalVisible(true);
    }

    const closeTagModal = () => {
        setIsTagModalVisible(false);
    }

    // 提交选择的标签
    const insertUserTags = (value) => {
        console.log(value.tags);
        if (value.tags.length > 0) {
            axios({
                method: 'POST',
                url: SERVER_URL + 'user_tag',
                data: {
                    tags: value.tags
                },
                headers: {
                    Authorization: getToken()
                }
            }).then((response) => {
                closeTagModal();
            })
        }
    }

    const login = (value) => {
        axios({
            method: 'POST',
            url: SERVER_URL + 'login',
            data: {
                username: value.username,
                password: value.password
            }
        }).then((response) => {
            if (response.data.status !== 200) {
                alert1.current.innerHTML = response.data.message;
                alert1.current.style.display = 'block';
            } else {
                if (value.remember) {
                    localStorage.setItem('token', response.data.token);
                } 
                sessionStorage.setItem('token', response.data.token);
                PubSub.publish('login', response.data.data);
                closeLoginModal();
            }
        })
    }

    const register = (value) => {
        if (value.confirm !== value.password) {
            alert2.current.innerHTML = '密码不一致';
            alert2.current.style.display = 'block';
            return;
        }
        axios({
            method: 'POST',
            url: SERVER_URL + 'register',
            data: {
                username: value.username,
                password: value.password,
                nickname: value.nickname
            }
        }).then((response) => {
            if (response.data.status === 200) {
                sessionStorage.setItem('token', response.data.token);
                PubSub.publish('login', response.data.data);
                closeRegisterModal();
                showTagModal();
            } else {
                this.alert2.innerHTML = response.data.message;
                this.alert2.style.display = 'block';
            }
        });
    }

    const search = (value) => {
        if (value.trim() !== '') {
            props.history.push(`/search?type=music&word=${value}`);
        }
    }

    const handleClick = (obj) => {
        if (obj.key === 'discover') {
            props.history.push('/discover');
        } else if (obj.key === 'my_music') {
            if (props.user) {
                props.history.push('/my_music');
            } else {
                showLoginModal();
            }
        } else if (obj.key === 'artist') {
            props.history.push('/artist_list?artist_location=hot');
        } else if (obj.key === 'top_list') {
            props.history.push('/top_list?type=total');
        }
    }

    return (
        <Header>
            <div style={{width: '1100px', margin: '0 auto', display: 'flex'}}>
                <div className="logo" style={{color: 'white'}}>
                    <span className="fa fa-music"></span>&nbsp;
                    718音乐
                </div>
                <Menu theme="dark" mode="horizontal" defaultSelectedKeys="discover" style={{marginLeft: 100}} onClick={handleClick}>
                    <Menu.Item key="discover">发现音乐</Menu.Item>
                    <Menu.Item key="top_list">排行榜</Menu.Item>
                    <Menu.Item key="artist">歌手</Menu.Item>
                    <Menu.Item key="my_music">我的音乐</Menu.Item>
                </Menu>
                <Search placeholder="搜索" style={{ width: '200px', display: 'block', margin: 'auto 50px auto 50px'}} onSearch={search} />
                {props.user ? <Dropdown overlay={userMenu}><Button type="link" style={{color: 'yellow', display: 'block', margin: 'auto 0 auto auto'}}>{props.user.username}</Button></Dropdown>
                : <Button type="primary" style={{display: 'block', margin: 'auto 0 auto auto'}} onClick={showLoginModal}>登录</Button>}
                <Modal title="登录" visible={isLoginModalVisible} onCancel={closeLoginModal} footer={[
                    <Button key="register" type="primary" style={{float: 'left'}} onClick={showRegisterModal}>注册</Button>,
                    <Button key="cancel" onClick={closeLoginModal}>取消</Button>,
                ]}>
                    <Form onFinish={login}>
                        <div ref={alert1} style={{backgroundColor: '#ffccc7', width: '100%', padding: '8px 15px', marginBottom: '10px', display: 'none'}}></div>
                        <Form.Item name="username" rules={[{ required: true, message: '用户名不能为空!' }]}>
                            <Input prefix={<UserOutlined />} placeholder="请输入用户名" />
                        </Form.Item>
                        <Form.Item name="password" rules={[{required: true, message: '密码不能为空!'}]}>
                            <Input.Password prefix={<LockOutlined />} placeholder="请输入密码" />
                        </Form.Item>
                        <Form.Item>
                            <Form.Item name="remember" valuePropName="checked" noStyle>
                                <Checkbox>记住我</Checkbox>
                            </Form.Item>
                            {/* <a className="login-form-forgot" href="" style={{float: 'right'}}>忘记密码</a> */}
                        </Form.Item>
                        <Form.Item>
                            <Button type="primary" htmlType="submit" style={{width: '100%'}}>登录</Button>
                        </Form.Item>
                    </Form>
                </Modal>
                <Modal title="注册" visible={isRegisterModalVisible} destroyOnClose onCancel={closeRegisterModal} footer={[
                    <Button key="register" type="primary" style={{float: 'left'}} onClick={showLoginModal}>登录</Button>,
                    <Button key="cancel" onClick={closeRegisterModal}>取消</Button>]}>
                    <Form onFinish={register}>
                        <div ref={alert2} style={{backgroundColor: '#ffccc7', width: '100%', padding: '8px 15px', marginBottom: '10px', display: 'none'}}></div>
                        <Form.Item name="username" label="用户名" rules={[{ required: true, message: '用户名不能为空!'}]}>
                            <Input placeholder="请输入用户名" />
                        </Form.Item>
                        <Form.Item name="password" label="密码" rules={[{required: true, message: '密码不能为空!'}]}>
                            <Input.Password placeholder="请输入密码" />
                        </Form.Item>
                        <Form.Item name="confirm" label="确认密码" dependencies={['password']} rules={[
                            {required: true, message: '密码不能为空!'},
                            ({ getFieldValue }) => ({
                                validator(_, value) {
                                    if (!value || getFieldValue('password') === value) {
                                        return Promise.resolve();
                                    }
                                    return Promise.reject(new Error('密码不一致!'));
                                },
                            })
                        ]}>
                            <Input.Password placeholder="请再次输入密码" />
                        </Form.Item>
                        <Form.Item name="nickname" label="昵称" rules={[{required: true, message: '昵称不能为空!'}]}>
                            <Input placeholder="请输入昵称" />
                        </Form.Item>
                        <Form.Item>
                            <Button type="primary" htmlType="submit" style={{width: '100%'}}>注册</Button>
                        </Form.Item>
                    </Form>
                </Modal>
                <Modal title="喜欢的类型" visible={isTagModalVisible} onCancel={closeTagModal} footer={null}>
                    <Form onFinish={insertUserTags}>
                        <Form.Item name="tags">
                            <Checkbox.Group options={tagList} />
                        </Form.Item>
                        <Form.Item>
                            <Button type="primary" htmlType="submit" style={{float: 'right'}}>确定</Button>
                        </Form.Item>
                    </Form>
                </Modal>
            </div>
        </Header>
    );
}

export default withRouter(MyHeader);