package com.linyuan.blog.controller;

import com.linyuan.blog.domain.Comment;
import com.linyuan.blog.domain.User;
import com.linyuan.blog.service.BlogService;
import com.linyuan.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author LinYuan
 * @version 1.0
 * @date 2019/12/26 15:06
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;

    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model){
        List<Comment> commentList = commentService.findCommentByBlogId(blogId);
        for (Comment comment : commentList) {
            comment.setAvatar((comment.getAvatar() != null) ? comment.getAvatar() : avatar);
            List<Comment> replyComments = comment.getReplyComments();
            if (replyComments != null){
                for (Comment replyComment : replyComments) {
                    replyComment.setAvatar((replyComment.getAvatar() != null) ? replyComment.getAvatar() : avatar);
                }
            }
        }
        model.addAttribute("comments",commentList);

        return "blog :: commentList";
    }

    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session){
        Long blogId = comment.getBlog().getId();
        comment.setBlog(blogService.findBlogById(blogId));
        User user = (User)session.getAttribute("user");
        if (user != null){
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        }else {
            comment.setAvatar(avatar);
        }
        commentService.insertComment(comment);
        return "redirect:/comments/" + blogId;
    }
}
