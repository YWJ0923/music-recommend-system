package com.ywj.music_recommend_system.back.service.impl;

import com.ywj.music_recommend_system.dao.AdminMapper;
import com.ywj.music_recommend_system.entity.Admin;
import com.ywj.music_recommend_system.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ywj
 * @date 2021/11/08
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;

    @Override
    public Admin login(String username, String password) {
        return adminMapper.getAdmin(username, password);
    }
}
