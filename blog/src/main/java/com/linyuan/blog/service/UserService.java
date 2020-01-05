package com.linyuan.blog.service;

import com.linyuan.blog.domain.User;

/**
 * @author LinYuan
 * @version 1.0
 * @date 2019/11/1 23:54
 */
public interface UserService {

    /**
     * 登陆验证
     * @param username
     * @param password
     * @return
     */
    User checkUser(String username, String  password);

    /**
     * 更新登陆时间
     * @param userId
     */
    void updateLoginTime(Long userId);
}
