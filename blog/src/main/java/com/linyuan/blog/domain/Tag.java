package com.linyuan.blog.domain;

import java.util.ArrayList;
import java.util.List;


/**
 * @author LinYuan
 * @version 1.0
 * @date 2019/11/1 23:04
 */


public class Tag {


    private Long tagId;

    private String tagName;

    private List<Blog> blogList;

    public Tag() {
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public List<Blog> getBlogList() {
        return blogList;
    }

    public void setBlogList(List<Blog> blogList) {
        this.blogList = blogList;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tagId=" + tagId +
                ", tagName='" + tagName + '\'' +
                ", blogList=" + blogList +
                '}';
    }
}
