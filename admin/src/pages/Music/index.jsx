import { Button, Card, Table, Form, Input } from 'antd'
import React, { Component } from 'react'
import axios from 'axios';

const colomns = [
    {
        title: '歌名',
        dataIndex: 'musicName',
    },
    {
        title: '歌手',
        dataIndex: 'artistName',
    },
    {
        title: '专辑',
        dataIndex: 'albumName'
    },
    {
        title: '播放量',
        dataIndex: 'playTimes'
    },
    {
        title: '操作',
        render: () => {
            return (
                <div>
                    <Button type="primary">修改</Button>&nbsp;
                    <Button type="danger">删除</Button>
                </div>
            );
        }
    }
];

export default class Music extends Component {
    state = {data: []};

    onFinish = (values) => {
        axios({
            method: 'GET',
            url: 'http://localhost:3000/admin/music',
            params: values
        }).then((response) => {
            console.log(response);
            this.setState({data: response.data});
        })
    }

    render() {
        return (
            <Card title="音乐管理" extra=
            {<Form layout="inline" onFinish={this.onFinish}>
                <Form.Item name="musicName">
                    <Input placeholder="歌名/歌手/专辑"/>
                </Form.Item>
                <Form.Item>
                    <Button type="primary" htmlType="submit">搜索</Button>
                </Form.Item>
            </Form>}>
                <Table columns={colomns} dataSource={this.state.data} ref={r => this.resultTable = r} />
            </Card>
        )
    }
}
