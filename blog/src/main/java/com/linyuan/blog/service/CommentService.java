package com.linyuan.blog.service;

import com.linyuan.blog.domain.Comment;

import java.util.List;

/**
 * @author LinYuan
 * @version 1.0
 * @date 2019/12/26 15:12
 */
public interface CommentService {

    List<Comment> findCommentByBlogId(Long blogId);

    Integer insertComment(Comment comment);
}
