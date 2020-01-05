package com.linyuan.blog.controller;

import com.linyuan.blog.NotFoundException;
import com.linyuan.blog.domain.BlogTypeVO;
import com.linyuan.blog.domain.Tag;
import com.linyuan.blog.domain.Type;
import com.linyuan.blog.service.BlogService;
import com.linyuan.blog.service.TagService;
import com.linyuan.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author LinYuan
 * @version 1.0
 * @date 2019/10/31 17:10
 */
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @RequestMapping("/")
    public String index(Model model){

        model.addAttribute("blogTop",blogService.findAllBlog(new BlogTypeVO()));

        List<Tag> tagList = tagService.findAllTag();
        for (Tag tag : tagList) {
            tag.setBlogList(blogService.findBlogByTagId(tag.getTagId()));
        }
        model.addAttribute("tagTop",tagList);

        List<Type> typeList = typeService.findAllType();
        for (Type type : typeList) {
            type.setBlogList(blogService.findAllBlog(new BlogTypeVO(type.getTypeId())));
        }
        model.addAttribute("typeTop", typeList);

        model.addAttribute("blogRecommend",blogService.findBlogRecommendTop(6));

        return "index";
    }
    @PostMapping("/search")
    public String search(Model model, @RequestParam String query){

        model.addAttribute("blogQuery",blogService.findBlogByQuery(query));

        model.addAttribute("query",query);

        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model){
        blogService.updateViews(id);
        model.addAttribute("blog",blogService.findAndConvertBlogById(id));
        return "blog";
    }

    @GetMapping("/footer/newblog")
    public String newblogs(Model model){
        model.addAttribute("newblogs",blogService.findBlogRecommendTop(3));
        return "_fragments :: newblogList";

    }
}
