package com.linyuan.blog.controller.admin;

import com.linyuan.blog.domain.Tag;
import com.linyuan.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author LinYuan
 * @version 1.0
 * @date 2019/11/5 10:32
 */

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String findAllTag(Model model) {
        model.addAttribute("tagsList",tagService.findAllTag());
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String input(Model model) {
        model.addAttribute("tagsList", new Tag());
        return "admin/tags-input";
    }

    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        Tag tag = tagService.findTagById(id);
        model.addAttribute("tagsList", tag);
        return "admin/tags-input";
    }


    @PostMapping("/tags")
    public String post(@Valid Tag tag,BindingResult result,
                       RedirectAttributes attributes,
                       Model model) {
        Tag tagCheck = tagService.findTagByName(tag.getTagName());
        if (tagCheck != null) {

            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        if (result.hasErrors()) {
            return "admin/tags-input";
        }
        Integer t = tagService.insertTag(tag);
        if (t == null ) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        model.addAttribute("tagsList",tagCheck);
        return "redirect:/admin/tags";
    }


    @PostMapping("/tags/{id}")
    public String editPost(@Valid Tag tag, BindingResult result,
                           @PathVariable Long id,
                           RedirectAttributes attributes,
                           Model model) {



        Tag tagCheck = tagService.findTagByName(tag.getTagName());

        if (tagCheck != null) {

            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        if (result.hasErrors()) {
            model.addAttribute("typeList", tagCheck);
            return "admin/tags-input";
        }
        Integer t = tagService.updateTag(id,tag);
        if (t == null ) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }

        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes) {
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/tags";
    }


}
