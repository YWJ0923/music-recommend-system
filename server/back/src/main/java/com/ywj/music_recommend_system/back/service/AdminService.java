package com.ywj.music_recommend_system.back.service;

import com.ywj.music_recommend_system.entity.Admin;

/**
 * @author ywj
 * @date 2021/11/08
 */
public interface AdminService {
    Admin login(String username, String password);
}
