import React, { Component } from 'react'
import { Form, Input, Button, Card } from 'antd';
import { UserOutlined, LockOutlined } from '@ant-design/icons';
import axios from 'axios';
import './index.css';

export default class Login extends Component {
    onFinish = (values) => {
        console.log('Received values of form: ', values);
        const {setLogin} = this.props;
        axios({
            method: 'GET',
            url: 'http://localhost:3000/admin/login',
            params: values
        }).then((response) => {
            console.log(response);
            if (response.data.resultCode === 1) {
                setLogin();
            }
        });
    }

    render() {
        return (
            <Card title="718音乐后台" className="login-card">
                <Form
                    name="normal_login"
                    className="login-form"
                    onFinish={this.onFinish}
                >
                    <Form.Item
                        name="username"
                        rules={[
                        {
                            required: true,
                            message: 'Please input your Username!',
                        },
                        ]}
                    >
                        <Input prefix={<UserOutlined className="site-form-item-icon" />} placeholder="Username" />
                    </Form.Item>
                    <Form.Item
                        name="password"
                        rules={[
                        {
                            required: true,
                            message: 'Please input your Password!',
                        },
                        ]}
                    >
                        <Input
                        prefix={<LockOutlined className="site-form-item-icon" />}
                        type="password"
                        placeholder="Password"
                        />
                    </Form.Item>
                    <Form.Item>
                        <Button type="primary" htmlType="submit" className="login-form-button">
                        Log in
                        </Button>
                    </Form.Item>
                </Form>
            </Card>
        )
    }
}
