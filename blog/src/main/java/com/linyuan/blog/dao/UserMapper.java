package com.linyuan.blog.dao;

import com.linyuan.blog.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;

/**
 * @author LinYuan
 * @version 1.0
 * @date 2019/11/1 23:58
 */
@Repository
@Mapper
public interface UserMapper {
    /**
     * 登陆验证
     * @param username
     * @param password
     * @return
     */
    User findByUsernameAndPassword(String username, String password);

    /**
     * 更新登陆时间
     * @param userId
     */
    void updateLoginTime(Long userId);
}
