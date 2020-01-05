package com.linyuan.blog.controller.admin;

import com.linyuan.blog.domain.Blog;
import com.linyuan.blog.domain.BlogTypeVO;
import com.linyuan.blog.domain.User;
import com.linyuan.blog.service.BlogService;
import com.linyuan.blog.service.TagService;
import com.linyuan.blog.service.TypeService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author LinYuan
 * @version 1.0
 * @date 2019/11/5 11:02
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public String blogs(BlogTypeVO blog, Model model){

        model.addAttribute("blogList",blogService.findAllBlog(blog));
        model.addAttribute("typeList",typeService.findAllType());
        return "admin/blogs";
    }

    @PostMapping("/blogs/search")
    public String search(BlogTypeVO blog, Model model){

        model.addAttribute("blogList",blogService.findAllBlog(blog));

        //只返回blog.html里面的blogList这个片段
        return "admin/blogs :: blogList";
   }

    @GetMapping("/blogs/input")
   public String input(Model model){

        model.addAttribute("blog",new Blog());

        model.addAttribute("typeList",typeService.findAllType());

        model.addAttribute("tagList",tagService.findAllTag());

        return "admin/blogs-input";
   }

    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id,Model model){

        model.addAttribute("typeList",typeService.findAllType());

        model.addAttribute("tagList",tagService.findAllTag());

        Blog blog = blogService.findBlogById(id);

        blog.initTagIdList();

        model.addAttribute("blog",blog);

        return "admin/blogs-input";
    }

    @PostMapping("/blogs")
   public String post(Blog blog, RedirectAttributes attributes, HttpSession session){

        blog.setUser((User) session.getAttribute("user"));

        blog.setTypeId(blog.getType().getTypeId());

        blog.setType(typeService.getType(blog.getType().getTypeId()));

        blog.setTagList(tagService.findTagList(blog.getTagIdList()));

        Integer checkBlog;

        if (blog.getId() == null){

             checkBlog = blogService.insertBlog(blog);
            if (checkBlog == null){
                attributes.addFlashAttribute("message","插入博客失败");

            }else {
                blogService.insertBlogTagId(blog.getId(),blog.getTagIdList());
                attributes.addFlashAttribute("message","插入博客成功");
            }
        }else {
             checkBlog = blogService.updateBlog(blog);

            if (checkBlog == null){

                attributes.addFlashAttribute("message","更新博客失败");

            }else {
                blogService.deleteBlogTagId(blog.getId());
                blogService.insertBlogTagId(blog.getId(), blog.getTagIdList());
                attributes.addFlashAttribute("message","更新博客成功");
            }
        }
       return "redirect:/admin/blogs";
   }

    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id, Blog blog, RedirectAttributes attributes){
        if(blog != null){
            Integer deleteBlog = blogService.deleteBlogById(id);
            if (deleteBlog != null){
                blogService.deleteBlogTagId(id);
                attributes.addFlashAttribute("message","操作成功");
            }else {
                attributes.addFlashAttribute("message","操作失败");
            }
        }
        return "redirect:/admin/blogs";
    }


}
