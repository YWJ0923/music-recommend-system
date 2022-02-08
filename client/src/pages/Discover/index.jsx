import React, {useEffect, useRef, useState} from 'react';
import {Button, Card, Carousel, Col, Image, Row} from "antd";
import {LeftOutlined, RightOutlined} from "@ant-design/icons";
import axios from "axios";
import {SERVER_URL} from "../../utils/constant";
import {Link} from "react-router-dom";
import {playBarStore} from "../../stores/playBarStore";

const { Meta } = Card;

export default function Discover() {
    const [musicList, setMusicList] = useState([]);
    const [albumList, setAlbumList] = useState([]);

    useEffect(() => {
        axios({
            method: 'GET',
            url: SERVER_URL + 'latestMusics'
        }).then((response) => {
            setMusicList(response.data.data);
        });
        axios({
            method: 'GET',
            url: SERVER_URL + 'latestAlbums'
        }).then((response) => {
            setAlbumList(response.data.data);
        });
    }, []);

    return (
        <>
            <div>
                <div style={{textAlign: 'center', margin: '0 0 20px 0', fontSize: '30px', fontWeight: 'bold'}}>新歌首发</div>
                <Row gutter={[0, 5]}>
                        {musicList.map((music) => {
                            return (<Col>
                                <div style={{height: '70px', display: 'flex', width: '315px'}}>
                                    <Link to={`/album/${music.albumId}`}>
                                        <img alt={music.albumName} src={music.albumImg} style={{width: '70px', height: '70px', marginRight: '10px'}} />
                                    </Link>
                                    <div style={{height: '70px'}}>
                                        <Button type="link" onClick={() => {playBarStore.addMusic(music);}} style={{padding: '0px'}}>
                                            <div style={{height: '35px', lineHeight: '15px', fontSize: '15px'}}>{music.musicName}</div>
                                        </Button>
                                        <Link to={`/artist/${music.artistId}`}>
                                            <div style={{height: '35px', lineHeight: '21px', fontSize: '13px', color: '#999'}}>{music.artistName}</div>
                                        </Link>
                                    </div>
                                </div>
                            </Col>);
                        })}
                </Row>
            </div>
            <div>
                <div style={{textAlign: 'center', margin: '20px 0', fontSize: '30px', fontWeight: 'bold'}}>新碟首发</div>
                <Row gutter={[10, 5]}>
                    {albumList.map((album) => {
                        return (<Col span={4.8}>
                            <div style={{height: '250px', width: '180px'}}>
                                <Link to={`/album/${album.albumId}`}>
                                    <img alt={album.albumName} src={album.albumImg} style={{width: '180px', height: '180px', marginRight: '10px'}} />
                                </Link>
                                <div style={{height: '60px'}}>
                                    <Link to={`/album/${album.albumId}`}>
                                        <Button type="text" style={{padding: '0'}}>
                                            <div style={{height: '30px', lineHeight: '15px', fontSize: '15px', marginTop: '5px', overflow: 'hidden'}}>{album.albumName}</div>
                                        </Button>
                                    </Link>
                                    <Link to={`/artist/${album.artistId}`}>
                                        <div style={{height: '30px', lineHeight: '21px', fontSize: '13px', color: '#999', overflow: 'hidden'}}>{album.artistName}</div>
                                    </Link>
                                </div>
                            </div>
                        </Col>);
                    })}
                </Row>
            </div>
        </>
    );
}