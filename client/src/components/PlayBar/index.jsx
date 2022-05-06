import React, { useEffect, useState, useRef } from 'react'
import {StepBackwardOutlined, StepForwardOutlined, CaretRightOutlined, PauseOutlined, UnorderedListOutlined, HeartOutlined, PlusCircleOutlined} from '@ant-design/icons';
import { Button, Dropdown, Menu, Modal, Image, Form, Input, Slider } from 'antd'
import PubSub from 'pubsub-js';
import axios from 'axios';
import { Link } from 'react-router-dom';
import {observer, useLocalObservable} from 'mobx-react';
import { getToken } from '../../utils/auth';
import { SERVER_URL } from '../../utils/constant';
import {playBarStore} from "../../stores/playBarStore";
import {showNotification} from "../../utils/common";
import {getPlayUrl} from "../../utils/play";

function PlayBar(props) {
    const {playList, index, setIndex, playUrl} = useLocalObservable(() => playBarStore);

    const [isPaused, setIsPaused] = useState(true);
    const [currentTime, setCurrentTime] = useState(0);
    const [beginTime, setBeginTime] = useState(undefined);
    const [duration, setDuration] = useState(0);
    const [volume, setVolume] = useState(0.3);
    const [collectionList, setCollectionList] = useState([]);
    const [isCollectionListModalVisible, setIsCollectionListModalVisible] = useState(false);
    const [isNewCollectionListModalVisible, setIsNewCollectionListModalVisible] = useState(false);

    const audio1 = useRef();

    useEffect(() => {
        PubSub.subscribe('showNewCollectionListModal', showNewCollectionListModal);
        PubSub.subscribe('addPlayTimes', addPlayTimes);
        return () => {
            PubSub.unsubscribe('showNewCollectionListModal');
            PubSub.unsubscribe('addPlayTimes');
        }  // eslint-disable-next-line
    }, []);

    const playBackward = () => {
        addPlayTimes();
        if (playList.length === 1) {
            audio1.current.currentTime = 0
            audio1.current.play();
        }
        else {
            const newIndex = (index - 1 + playList.length) % playList.length;
            getPlayUrl(playList[newIndex]);
            setIndex(newIndex);
        }
    }
    
    const playOrPause = () => {
        isPaused ? audio1.current.play() : audio1.current.pause();
        setIsPaused(!isPaused);
    }

    const playForward = () => {
        addPlayTimes();
        if (playList.length === 1) {
            audio1.current.currentTime = 0
            audio1.current.play();
        }
        else {
            const newIndex = (index + 1) % playList.length;
            getPlayUrl(playList[newIndex]);
            setIndex(newIndex);
        }
    }

    // 播放列表切换歌
    const changeMusic = (obj) => {
        // console.log(obj.key);
        addPlayTimes();
        const newIndex = playList.findIndex((music) => {
            return music.musicId === obj.key;
        });
        if (newIndex !== index) {
            getPlayUrl(playList[newIndex]);
            setIndex(newIndex);
        }
        // console.log(newIndex);
    }

    // 播放列表
    const createPlayList = () => {
        if (playList[index]) {
            return (
                <Menu selectedKeys={[playList[index].musicId]} onClick={changeMusic} style={{width: '800px', height: '300px', overflow: 'scroll', overflowX: 'hidden'}}>
                    <Menu.Item key="title" style={{width: '750px'}}>播放列表 ({playList.length})</Menu.Item>
                    <Menu.Divider />
                    {playList.map((music) => {
                        return (
                            <>
                                <Menu.Item key={music.musicId}>
                                    <div style={{display: 'flex'}}>
                                        <div style={{width: '450px'}}>{music.musicName}</div>
                                        <div style={{width: '250px'}}>{music.artistName}</div>
                                        <div style={{width: '50px'}}>{parseTime(music.timeLength / 1000)}</div>
                                    </div>
                                </Menu.Item>
                                <Menu.Divider />
                            </>
                        );
                    })}
                </Menu>
            );
        } else {
            return (
                <Menu>
                    <Menu.Item key="title" style={{width: '650px'}}>播放列表 ({playList.length})</Menu.Item>
                    <Menu.Divider />
                </Menu>
            );
        }
        
    }

    // 音乐加载完毕，可以播放
    const canPlay = () => {
        setIsPaused(false);
        setBeginTime(new Date().getTime());
        console.log(beginTime);
        setDuration(audio1.current.duration);
        audio1.current.play();
    }

    // audio更新state时间
    const updatePlayTime = () => {
        setCurrentTime(audio1.current.currentTime);
    }

    // 停留时间超过30秒，增加播放量、用户播放量，换歌的时候执行，添加用户评分
    const addPlayTimes = () => {
        if (playList[index]) {
            const endTime = new Date().getTime();
            // console.log(beginTime);
            // console.log(endTime);
            if (endTime - beginTime >= 30000) {
                axios({
                    method: 'POST',
                    url: SERVER_URL +  'play_times/' + playList[index].musicId
                }).then((response) => {
                    console.log(response.data);
                });
                if (props.user) {
                    axios({
                        method: 'POST',
                        url: SERVER_URL +  'user/play_times/' + playList[index].musicId,
                        headers: {
                            Authorization: getToken()
                        },
                        data: {
                            time_length: endTime - beginTime
                        }
                    }).then((response) => {
                        if (response.data.status === 401) {
                            PubSub.publish('logout');
                        }
                    });
                }
            }
        }
    }
    
    // 拖动条改变时间
    const changePlayTime = (value) => {
        audio1.current.currentTime = value;
    }
    
    // 解析时间成00:00格式, time以秒为单位
    const parseTime = (time) => {
        var min = Math.floor(time / 60);
        if (min < 10) {
            min = '0' + min;
        }
        var sec = Math.floor(time % 60);
        if (sec < 10) {
            sec = '0' + sec;
        }
        return min + ':' + sec;
    }
    
    // audio更新音量
    const updateVolume = () => {
        setVolume(audio1.current.volume);
    }

    // 静音或者音量最大
    const mute = () => {
        audio1.current.volume = audio1.current.volume > 0 ? 0 : 1;
    }

    // 拖动条改变音量
    const changeVolume = (value) => {
        audio1.current.volume = value;
    }

    const showCollectionListModal = () => {
        if (!props.user) {
            PubSub.publish('showLoginModal');
        } else{
            axios({
                method: 'GET',
                url: SERVER_URL + 'collection_list',
                headers: {
                    Authorization: getToken()
                }
            }).then((response) => {
                if (response.data.status !== 200) {
                    PubSub.publish('logout');
                    PubSub.publish('showLoginModal');
                } else{
                    setCollectionList(response.data.data);
                    setIsCollectionListModalVisible(true);
                }
            });
        }
    }

    const closeCollectionListModal = () => {
        setIsCollectionListModalVisible(false);
    }

    const showNewCollectionListModal = () => {
        setIsCollectionListModalVisible(false);
        setIsNewCollectionListModalVisible(true);
    }

    const closeNewCollectionListModal = () => {
        setIsNewCollectionListModalVisible(false);
    }

    const handleCollection = (obj) => {
        if (playList[index]) {
            if (obj.key === 'newList') {
                showNewCollectionListModal();
            } else {
                insertMusicToCollectionList(obj.key, playList[index].musicId);
            }
        }
        closeCollectionListModal();
    }

    const insertMusicToCollectionList = (collectionListId, musicId) => {
        axios({
            method: 'POST',
            url: SERVER_URL + 'collection_list/' + collectionListId + '/' + musicId,
            headers: {
                Authorization: getToken()
            }
        }).then((response) => {
            if (response.data.status === 401) {
                PubSub.publish('logout');
            }
            showNotification(response.data.message);
        })
    }

    const insertCollectionList = (values) => {
        axios({
            method: 'POST',
            url: SERVER_URL + 'collection_list/' + values.collectionListName,
            headers: {
                Authorization: getToken()
            }
        }).then((response) => {
            if (response.data.status === 401) {
                PubSub.publish('logout');
            } else if (response.data.status === 200) {
                PubSub.publish('getCollectionList');
            }
            showNotification(response.data.message);
        });
        closeNewCollectionListModal();
    }

    return (
        <>
            <div style={{position: 'fixed', left: 0, bottom: 0, height: '60px', backgroundColor: 'black', width: '100%'}} id="playbar">
                <div style={{margin: '5px auto', width: '1000px', display: 'flex'}}>
                    <Button shape='circle' style={{backgroundColor: 'black', margin: 'auto 5px', borderWidth: '2px'}} onClick={playBackward}>
                        <StepBackwardOutlined style={{color: 'white', fontSize: '25px', marginTop: '-2px'}} />
                    </Button>
                    <Button shape='circle' style={{backgroundColor: 'black', margin: 'auto 5px', borderWidth: '2px'}} onClick={playOrPause} size="large">
                        {isPaused ? <CaretRightOutlined style={{color: 'white', fontSize: '32px', marginLeft: '4px', marginTop: '-4px'}} /> : <PauseOutlined style={{color: 'white', fontSize: '32px', marginLeft: '0', marginTop: '-4.5px'}} />}
                    </Button>
                    <Button shape='circle' style={{backgroundColor: 'black', margin: 'auto 5px', borderWidth: '2px'}} onClick={playForward}>
                        <StepForwardOutlined style={{color: 'white', fontSize: '25px', marginTop: '-2px'}} />
                    </Button>
                    {playList[index] ? 
                    <Link to={`/album/${playList[index].albumId}`}>
                        <img alt={playList[index].musicName} src={playList[index].albumImg} style={{width: '50px', height: '50px', margin: '0 10px'}} />
                    </Link> 
                    : <img alt="718音乐" src="/favicon.ico" style={{width: '50px', height: '50px', margin: '0 10px'}} />}
                    <div style={{width: '550px'}}>
                        <div style={{color: '#e8e8e8'}}>
                            {playList[index] ? playList[index].musicName : '718音乐'}
                            {playList[index] && <Link to={`/artist/${playList[index].artistId}`} style={{color: '#9b9b9b'}}><span style={{fontSize: '10px', marginLeft: '20px'}}>{playList[index].artistName}</span></Link>}
                        </div>
                        <div className="barAndTime" style={{marginTop: '8px', width: '550px', display: 'flex'}}>
                            <div className="bar" style={{width: '450px', height: '10px', marginTop: '7px', display: 'flex'}}>
                                {/* <input type="range" max={duration} value={currentTime} style={{backgroundColor: 'red', height: '10px', width: '450px', marginBottom: '5px'}} onChange={changePlayTime} /> */}
                                <Slider max={duration} value={currentTime} style={{height: '10px', width: '450px', marginTop: '-1px', marginLeft: '-1px'}} onChange={changePlayTime} tooltipVisible={false} />
                            </div>
                            <div className="time" style={{color: 'white', marginLeft: '5px'}}>{parseTime(currentTime)}/{parseTime(duration)}</div>
                        </div>
                    </div>
                    <div style={{margin: 'auto 5px'}}>
                        <Button icon={<HeartOutlined />} title="收藏" ghost size="small" style={{border: '0'}} onClick={showCollectionListModal} />
                    </div>
                    <Dropdown overlay={createPlayList} placement="topCenter" arrow getPopupContainer={() => document.getElementById('playbar')}>
                        <Button style={{marginTop: '10px', border: '0'}} icon={<UnorderedListOutlined />} ghost />
                    </Dropdown>
                    <div className="volumeCtrl" style={{marginLeft: '10px', display: 'flex'}}>
                        <Button icon={volume !== 0 ? <i className="fa fa-volume-up"></i> : <i className="fa fa-volume-off"></i>} style={{marginTop: '10px', border: '0'}} ghost onClick={mute} />
                        {/* <input type="range" step="0.01" max="1" value={volume} onChange={changeVolume} /> */}
                        <Slider max={1} value={volume} onChange={changeVolume} step={0.01} style={{width: '100px', marginTop: '20px'}} tooltipVisible={false} />
                    </div>
                </div>
            </div>
            <audio src={playUrl} proload="auto" onCanPlay={canPlay} onTimeUpdate={updatePlayTime} onVolumeChange={updateVolume} onEnded={playForward} ref={audio1} />
            <Modal title="添加到歌单" onCancel={closeCollectionListModal} visible={isCollectionListModalVisible} okText="确认" cancelText="取消" footer={null}>
                <Menu onClick={handleCollection} style={{width: '480px', height: '400px', overflow: 'scroll', overflowX: 'hidden'}} defaultSelectedKeys="newList">
                    <Menu.Item key="newList" icon={<PlusCircleOutlined />}>
                        创建新歌单
                    </Menu.Item>
                    <Menu.Divider />
                    {collectionList.map((list) => {
                        return (
                            <>
                                <Menu.Item key={list.collectionListId} style={{height: '50px'}}>
                                    <div style={{display: 'flex'}}>
                                        <Image style={{width: '50px', height: '50px'}} src={list.collectionListImg} />
                                        <div style={{width: '250px', margin: 'auto 20px'}}>{list.collectionListName}</div>
                                        <div style={{width: '100px', margin: 'auto 10px'}}>{list.collectionListLength + '首'}</div>
                                    </div>
                                </Menu.Item>
                                <Menu.Divider />
                            </>
                        );
                    })}
                </Menu> 
            </Modal>
            <Modal title="创建新歌单" onCancel={closeNewCollectionListModal} visible={isNewCollectionListModalVisible} footer={null}>
                <Form onFinish={insertCollectionList}>
                    <Form.Item label="歌单名" name="collectionListName" rules={[{ required: true, message: '歌单名不能为空' }]}>
                        <Input />
                    </Form.Item>
                    <Form.Item wrapperCol={{ offset: 8, span: 16 }}>
                        <Button type="primary" htmlType="submit">新建</Button>
                    </Form.Item>
                </Form>
            </Modal>
        </>
    );
}

export default observer(PlayBar);
