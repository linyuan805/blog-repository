package com.linyuan.blog.controller;

import com.linyuan.blog.domain.Blog;
import com.linyuan.blog.domain.Tag;
import com.linyuan.blog.service.BlogService;
import com.linyuan.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.rmi.MarshalledObject;
import java.util.List;

/**
 * @author LinYuan
 * @version 1.0
 * @date 2020/1/3 12:45
 */
@Controller
public class TagShowController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping("tags/{id}")
    public String tags(@PathVariable Long id, Model model){

        List<Tag> tagList = tagService.findAllTag();
        if (id == -1){
            id = tagList.get(0).getTagId();
        }

        for (Tag tag : tagList) {
            tag.setBlogList(blogService.findBlogByTagId(tag.getTagId()));
        }

        model.addAttribute("tags",tagList);
        List<Blog> blogList = blogService.findBlogByTagId(id);
        for (Blog blog : blogList) {
            blog.setTagList(tagService.findTagByBlogId(blog.getId()));
        }
        model.addAttribute("blogs", blogList);
        model.addAttribute("activeTagId", id);

        return "tags";
    }

}


