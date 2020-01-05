package com.linyuan.blog.controller;

import com.linyuan.blog.domain.Blog;
import com.linyuan.blog.domain.BlogTypeVO;
import com.linyuan.blog.domain.Type;
import com.linyuan.blog.service.BlogService;
import com.linyuan.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author LinYuan
 * @version 1.0
 * @date 2020/1/2 22:01
 */
@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@PathVariable Long id, Model model){
        List<Type> typeList = typeService.findAllType();
        if (id == -1){
            id = typeList.get(0).getTypeId();
        }
        for (Type type : typeList) {
            type.setBlogList(blogService.findAllBlog(new BlogTypeVO(type.getTypeId())));
        }

        model.addAttribute("types", typeList);
        model.addAttribute("blogs",blogService.findAllBlog(new BlogTypeVO(id)));
        model.addAttribute("activeTypeId",id);

        return "types";
    }
}
