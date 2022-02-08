package com.ywj.music_recommend_system.back.dao;

import com.ywj.music_recommend_system.entity.Admin;
import org.springframework.stereotype.Repository;

/**
 * @author ywj
 * @date 2021/11/08
 */
@Repository
public interface AdminMapper {
    Admin getAdmin(String username, String password);
}
