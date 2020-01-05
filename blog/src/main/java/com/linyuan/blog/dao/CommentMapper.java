package com.linyuan.blog.dao;

import com.linyuan.blog.domain.Comment;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LinYuan
 * @version 1.0
 * @date 2019/12/26 15:25
 */
@Repository
public interface CommentMapper {

    /**
     * 根据blogid找到所有的评论
     * @param blogId
     * @return
     */
    List<Comment> findCommentByBlogIdAndParentIdIsNull(Long blogId);

    /**
     * 根据id查找评论
     * @param commentId
     * @return
     */
    Comment findCommentById(Long commentId);

    /**
     * 插入comment
     * @param comment
     * @return
     */
    Integer insertComment(Comment comment);

    /**
     * 根据parentid找到所有reply
     * @param commentId
     * @return
     */
    List<Comment> findReplyCommentByParentId(Long commentId);
}
