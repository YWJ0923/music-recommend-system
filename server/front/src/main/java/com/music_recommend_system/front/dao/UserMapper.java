package com.music_recommend_system.front.dao;

import com.music_recommend_system.front.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @Author ywj
 * @Date 2021/11/30
 */
@Repository
public interface UserMapper {
    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User getUserByUsername(String username);

    /**
     * 根据用户名和密码查询用户
     * @param username
     * @param password
     * @return
     */
    User getUserByUserNameAndPassword(String username, String password);

    /**
     * 根据用户id和用户名查询用户
     * @param userId
     * @param username
     * @return
     */
    User getUserByUserIdAndUsername(Integer userId, String username);

    /**
     * 插入用户
     * @param username
     * @param password
     * @param nickname
     * @return
     */
    int insertUser(String username, String password, String nickname);

    /**
     * 统计用户总数
     * @return
     */
    int countUser();
}
