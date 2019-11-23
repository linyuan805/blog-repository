package com.linyuan.blog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author LinYuan
 * @version 1.0
 * @date 2019/11/5 11:02
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    @RequestMapping("/blogs")
    @ResponseBody
    public String blogs(){
        return "admin/blogs";
    }


}
