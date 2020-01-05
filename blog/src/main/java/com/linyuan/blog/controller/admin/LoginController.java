package com.linyuan.blog.controller.admin;

import com.linyuan.blog.domain.User;
import com.linyuan.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author LinYuan
 * @version 1.0
 * @date 2019/11/2 0:10
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    /**
     * 如果查询checkuser不为空，则返回index页面
     * @param username
     * @param password
     * @param session
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes redirectAttributes){

        User checkUser = userService.checkUser(username, password);

        if (checkUser != null){
            checkUser.setPassword(null);
            userService.updateLoginTime(checkUser.getUserId());
            session.setAttribute("user", checkUser);
            return "admin/index";
        }
        else {

            redirectAttributes.addFlashAttribute("message","用户名或密码错误");
            return "redirect:/admin";
        }

    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.removeAttribute("user");
        return "redirect:/admin";
    }


}
