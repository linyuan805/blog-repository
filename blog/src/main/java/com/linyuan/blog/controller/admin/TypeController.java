package com.linyuan.blog.controller.admin;

import com.linyuan.blog.domain.Type;
import com.linyuan.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author LinYuan
 * @version 1.0
 * @date 2019/11/15 15:17
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(Model model){
        List<Type> typeList = typeService.findAllType();
        model.addAttribute("typeList",typeList);
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String typesInput(Model model){

        model.addAttribute("typeList", new Type());

        return "admin/types-input";
    }

    @PostMapping("/types")
    public String postType(Type type, RedirectAttributes attributes){
        Type checkTypeName = typeService.checkTypeNameNotExit(type);
        if (checkTypeName == null){

            typeService.insertType(type);

        }else {
            attributes.addFlashAttribute("message","分类已存在");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable long id, Model model){
        model.addAttribute("typeList", typeService.getType(id));
        return "admin/types-input";
    }

    @PostMapping("/types/{id}")
    public String editPost(Type type, @PathVariable long id){

        typeService.updateTypeName(type);
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable long id){
        typeService.deleteTypeById(id);
        return "redirect:/admin/types";
    }
}
