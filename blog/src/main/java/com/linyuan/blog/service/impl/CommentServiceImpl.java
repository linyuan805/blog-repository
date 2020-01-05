package com.linyuan.blog.service.impl;

import com.linyuan.blog.dao.CommentMapper;
import com.linyuan.blog.domain.Comment;
import com.linyuan.blog.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author LinYuan
 * @version 1.0
 * @date 2019/12/26 15:24
 */
@Service
public class CommentServiceImpl implements CommentService {


    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> findCommentByBlogId(Long blogId) {
        List<Comment> comment = commentMapper.findCommentByBlogIdAndParentIdIsNull(blogId);

        return eachComment(comment);
    }


    @Override
    public Integer insertComment(Comment comment) {

        Long parentCommentId = comment.getParentComment().getCommentId();

        if (parentCommentId != -1){
            comment.setParentComment(commentMapper.findCommentById(parentCommentId));
        }else {
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        return commentMapper.insertComment(comment);
    }

    /**
     * 循环每个顶级的评论节点
     * 这里传进来的comments是parent都为空的，也即顶级评论
     * @param comments
     * @return
     */
    private List<Comment> eachComment(List<Comment> comments) {
        List<Comment> commentsView = new ArrayList<>();
        for (Comment comment : comments) {
            List<Comment> replycomment = commentMapper.findReplyCommentByParentId(comment.getCommentId());

            for (Comment comment1 : replycomment) {
                comment1.setParentComment(comment);
            }
            comment.setReplyComments(replycomment);
            Comment c = new Comment();
            BeanUtils.copyProperties(comment,c);
            commentsView.add(c);
        }
        //合并评论的各层子代到第一级子代集合中
        combineChildren(commentsView);
        return commentsView;
    }


    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();
    /**
     * 这里传进来的是父节点
     * @param comments root根节点，blog不为空的对象集合
     * @return
     */
    private void combineChildren(List<Comment> comments) {

        for (Comment comment : comments) {
            List<Comment> replys1 = comment.getReplyComments();
            for(Comment reply1 : replys1) {

                //顶节点添加到临时存放集合
                tempReplys.add(reply1);
                //循环迭代，找出子代，存放在tempReplys中
                recursively(reply1);
            }
            //修改顶级节点的reply集合为迭代处理后的集合
            comment.setReplyComments(tempReplys);
            //清除临时存放区,迭代完一个就要清空。
            tempReplys = new ArrayList<>();
        }
    }


    /**
     * 递归迭代，剥洋葱
     * 这里传进来的是子节点，找到当前子节点的所有下属节点
     * @param comment 被迭代的对象
     * @return
     */
    private void recursively(Comment comment) {

        comment.setReplyComments(commentMapper.findReplyCommentByParentId(comment.getCommentId()));

        if (comment.getReplyComments().size()>0) {
            List<Comment> replys = comment.getReplyComments();
            for (Comment reply : replys) {
                reply.setParentComment(comment);
                tempReplys.add(reply);
                reply.setReplyComments(commentMapper.findReplyCommentByParentId(reply.getCommentId()));
                if (reply.getReplyComments().size()>0) {
                    recursively(reply);
                }
            }
        }
    }
}
