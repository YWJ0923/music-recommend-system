import React, { Component } from 'react'
import 'antd/dist/antd.min.css';
import { Route, Routes } from 'react-router-dom';
import Login from './pages/Login';
import Home from './pages/Home';


export default class App extends Component {
  state = {login: false}

  setLogin = () => {
    this.setState({login: true});
  }
  
  render() {
    return (
      <Routes>
        <Route path="/login" element={<Login setLogin={this.setLogin} />} />
        <Route path="/*" element={<Home />} />
      </Routes>
    );
  }
}

