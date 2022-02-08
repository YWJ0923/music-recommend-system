package com.ywj.music_recommend_system.back.dao;

import com.ywj.music_recommend_system.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @Author ywj
 * @Date 2021/11/30
 */
@Repository
public interface UserMapper {
    User getUserByUsername(String username);

    User getUserByUserNameAndPassword(String username, String password);

    User getUserByUserIdAndUsername(Integer userId, String username);

    Integer insertUser(String username, String password, String nickname);

}
