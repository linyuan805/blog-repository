package com.linyuan.blog.service.impl;

import com.linyuan.blog.dao.UserMapper;
import com.linyuan.blog.domain.User;
import com.linyuan.blog.service.UserService;
import com.linyuan.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LinYuan
 * @version 1.0
 * @date 2019/11/1 23:56
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User checkUser(String username, String password) {

        return userMapper.findByUsernameAndPassword(username, MD5Utils.code(password) );

    }

    @Override
    public void updateLoginTime(Long userId) {
        userMapper.updateLoginTime(userId);
    }
}
